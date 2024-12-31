import { GroupApi } from '$lib/api/group.api';
import type { PageServerLoad, PageServerLoadEvent } from '../$types';

export const load: PageServerLoad = async (event: PageServerLoadEvent) => {
	const response = await GroupApi.getGroups(event);
	const groups = response.ok ? response.data : [];
	return {
		groups: groups,
		session: await event.locals.auth()
	};
};
