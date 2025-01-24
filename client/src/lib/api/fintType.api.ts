import type { CreateFineTypeDto, FineTypeDto } from '$lib/models/fineTypes';
import { fetchData, type ApiEvent } from './fetcher';

const createFineType = async (event: ApiEvent, createFineTypeDto: CreateFineTypeDto) => {
	return await fetchData<FineTypeDto, CreateFineTypeDto>(
		event,
		'/api/v1/groups/fineTypes',
		'POST',
		createFineTypeDto
	);
};

export const fineTypeApi = { createFineType };
