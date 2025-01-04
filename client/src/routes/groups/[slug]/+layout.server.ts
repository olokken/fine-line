import { GroupApi } from '$lib/api/group.api';
import { redirect } from '@sveltejs/kit';
import type { LayoutServerLoad, LayoutServerLoadEvent } from './$types';

export const load: LayoutServerLoad = async (event: LayoutServerLoadEvent) => {
	const { slug } = event.params;

	const response = await GroupApi.getGroup(event, Number(slug));

	if (!response.ok) {
		return redirect(301, '/');
	}

	return {
		group: response.data
	};
};
