import type { Session } from '@auth/sveltekit';
import type { RequestEvent, ServerLoadEvent } from '@sveltejs/kit';

export type ApiEvent = RequestEvent | ServerLoadEvent;

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

const getTokenFromEvent = async (event: ApiEvent): Promise<string> => {
	const session = await event.locals.auth();
	return getTokenFromSession(session);
};

const getTokenFromSession = (session: Session | null) => {
	// eslint-disable-next-line @typescript-eslint/ban-ts-comment
	//@ts-ignore
	const token = session?.sessionToken as string;

	return token ?? '';
};

export type ApiErrorType = {
	status?: number;
	message: string;
	detailMessage: Nullable<string>;
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
	event: ApiEvent,
	endpoint: string,
	requestMethod: RequestMethod,
	body?: U
): Promise<ApiResponse<T>> => {
	const token = await getTokenFromEvent(event);
	return await sendRequest<T, U>(token, endpoint, requestMethod, body);
};

export const fetchDataFromClient = async <T, U = unknown>(
	session: Nullable<Session>,
	endpoint: string,
	requestMethod: RequestMethod,
	body?: U
): Promise<ApiResponse<T>> => {
	const token = await getTokenFromSession(session);
	return await sendRequest<T, U>(token, endpoint, requestMethod, body);
};

const sendRequest = async <T, U = unknown>(
	token: string,
	endpoint: string,
	requestMethod: RequestMethod,
	body?: U
): Promise<ApiResponse<T>> => {
	try {
		const requestInit: RequestInit = {
			method: requestMethod,
			headers: getHeaders(token)
		};

		if (body) {
			requestInit.body = body instanceof FormData ? body : JSON.stringify(body);
		}

		const baseUrl = import.meta.env.VITE_API_BASE_URL;

		const response = await fetch(`${baseUrl}${endpoint}`, requestInit);

		if (response.ok) {
			return { ok: true, data: (await response.json()) as T };
		}

		const errorResponse = await response.text();

		return {
			ok: false,
			error: {
				status: response.status,
				message: response.statusText,
				detailMessage: errorResponse.length != 0 ? errorResponse : null
			}
		};
	} catch {
		return {
			ok: false,
			error: { message: 'Unknown error', detailMessage: null }
		};
	}
};
