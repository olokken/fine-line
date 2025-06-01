import { FineApi } from '$lib/api/fine.api';
import type { CreateFineDto } from '$lib/models/fine';
import type { Actions } from '@sveltejs/kit';

export const actions = {
	default: async (event) => {
		const formData = await event.request.formData();

		const createFineDto: CreateFineDto = {
			groupId: Number(formData.get('groupId')),
			receiverId: formData.get('recieverId') as string,
			fineTypeId: Number(formData.get('fineTypeId'))
		};

		return await FineApi.issueFine(event, createFineDto);
	}
} satisfies Actions;
