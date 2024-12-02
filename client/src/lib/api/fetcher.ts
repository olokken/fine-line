import { tokenCookieName } from '$lib/utils/constants';
import type { ServerLoadEvent } from '@sveltejs/kit';

export type RequestMethod = 'GET' | 'PUT' | 'POST' | 'DELETE' | 'PATCH';

const getHeaders = (token: string): HeadersInit => {
	if (!token) {
		return {};
	}

	const headersInit: HeadersInit = {
		'Content-Type': 'application/json',
		Authorization: `Bearer ${token}`
	};

	return headersInit;
};

const getTokenFromEvent = (event: ServerLoadEvent): string => {
	const token = event.cookies.get(tokenCookieName);

	return token ?? '';
};

export type ApiErrorType = {
	status?: number;
	message: string;
	detailMessage?: string;
};

export type ApiSuccessResponse<T> = {
	ok: true;
	data: T;
};

export type ApiErrorResponse = {
	ok: false;
	error: ApiErrorType;
};

export type ApiResponse<T = unknown> = ApiSuccessResponse<T> | ApiErrorResponse;

export const fetchData = async <T, U = unknown>(
	event: ServerLoadEvent,
	url: string,
	requestMethod: RequestMethod,
	body?: U
): Promise<ApiResponse<T>> => {
	try {
		const token = getTokenFromEvent(event);
		const requestInit: RequestInit = {
			method: requestMethod,
			headers: getHeaders(token)
		};

		if (body) {
			requestInit.body = body instanceof FormData ? body : JSON.stringify(body);
		}

		const response = await fetch(url, requestInit);

		if (response.ok) {
			return { ok: true, data: (await response.json()) as T };
		}

		const errorResponse = await response.json();

		return {
			ok: false,
			error: {
				status: response.status,
				message: response.statusText,
				detailMessage: errorResponse.errors?.detail
			}
		};
	} catch {
		return {
			ok: false,
			error: { message: 'Unknown error' }
		};
	}
};
