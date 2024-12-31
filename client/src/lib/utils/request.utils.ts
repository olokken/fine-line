import type { ApiResponse } from '$lib/api/fetcher';
import type { ToastSettings } from '@skeletonlabs/skeleton';

export const getToastFromResponse = (
	apiResponse: Nullable<ApiResponse>,
	successMessage: string
): Nullable<ToastSettings> => {
	if (!apiResponse) {
		return null;
	}

	if (!apiResponse.ok) {
		return {
			message: apiResponse.error.detailMessage ?? apiResponse.error.message,
			background: 'variant-filled-error',
			timeout: 3000
		};
	}

	return {
		message: successMessage,
		background: 'variant-filled-success',
		timeout: 3000
	};
};
