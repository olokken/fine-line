import type { CreateFineDto } from '$lib/models/fine';
import { fetchData, type ApiEvent } from './fetcher';

const issueFine = async (event: ApiEvent, createFineDto: CreateFineDto) => {
	return await fetchData<number, CreateFineDto>(event, '/api/v1/groups/fines', 'POST', createFineDto);
};

export const FineApi = { issueFine };
