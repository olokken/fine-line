import { GroupApi } from '$lib/api/group.api';
import type { CreateGroupDto } from '$lib/models/group';
import type { Actions } from './$types';

export const actions = {
	default: async (event) => {
		const formData = await event.request.formData();

		const createGroupDto: CreateGroupDto = {
			name: formData.get('name') as string
		};

		return await GroupApi.createGroup(event, createGroupDto);
	}
} satisfies Actions;
