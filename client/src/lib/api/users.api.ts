import type { User } from '$lib/models/user';
import type { ServerLoadEvent } from '@sveltejs/kit';
import { fetchData, type ApiResponse } from './fetcher';

const createOrGetUser = async (event: ServerLoadEvent): Promise<ApiResponse<User>> => {
	return await fetchData<User>(event, '/api/v1/users', 'POST');
};

export const UserApi = { createOrGetUser };
