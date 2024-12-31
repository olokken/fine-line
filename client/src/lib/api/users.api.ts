import type { User } from '$lib/models/user';
import { fetchData, type ApiEvent, type ApiResponse } from './fetcher';

const createOrGetUser = async (event: ApiEvent): Promise<ApiResponse<User>> => {
	return await fetchData<User>(event, '/api/v1/users', 'POST');
};

export const UserApi = { createOrGetUser };
