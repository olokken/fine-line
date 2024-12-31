import type { ApiResponse } from '$lib/api/fetcher';
import { UserApi } from '$lib/api/users.api';
import type { User } from '$lib/models/user';
import type { LayoutServerLoad } from './$types';

export const load: LayoutServerLoad = async (event) => {
	const session = await event.locals.auth();

	const user: ApiResponse<User> = await UserApi.createOrGetUser(event);

	if (!user.ok) return { session };

	return {
		groups: user.data.groups,
		userId: user.data.userId,
		name: user.data.name,
		session
	};
};
