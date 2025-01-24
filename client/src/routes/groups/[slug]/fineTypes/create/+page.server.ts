import { fineTypeApi } from '$lib/api/fintType.api';
import type { CreateFineTypeDto } from '$lib/models/fineTypes';
import type { Actions } from './$types';

export const actions = {
	default: async (event) => {
		const formData = await event.request.formData();

		const createFineTypeDto: CreateFineTypeDto = {
			groupId: Number(formData.get('groupId')),
			name: formData.get('name') as string,
			description: formData.get('description') as string,
			sum: Number(formData.get('sum'))
		};

		return await fineTypeApi.createFineType(event, createFineTypeDto);
	}
} satisfies Actions;
