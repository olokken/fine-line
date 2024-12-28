import { UserApi } from '$lib/api/users.api';
import type { LayoutServerLoad } from './$types';

export const load: LayoutServerLoad = async (event) => {
	const session = await event.locals.auth();

	await UserApi.createOrGetUser(event);

	return {
		session
	};
};
