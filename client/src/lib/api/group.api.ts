import type { CreateGroupDto, GroupDetailDto, GroupDto } from '$lib/models/group';
import { fetchData, type ApiEvent } from './fetcher';

const createGroup = async (event: ApiEvent, createGroupDto: CreateGroupDto) => {
	return await fetchData<GroupDto[], CreateGroupDto>(
		event,
		'/api/v1/groups',
		'POST',
		createGroupDto
	);
};

const getGroups = async (event: ApiEvent) => {
	return await fetchData<GroupDto[]>(event, '/api/v1/groups', 'GET');
};

const getGroup = async (event: ApiEvent, groupId: number) => {
	return await fetchData<GroupDetailDto>(event, `/api/v1/groups/${groupId}`, 'GET');
};

export const GroupApi = { createGroup, getGroups, getGroup };
