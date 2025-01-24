import type { FineTypeDto } from './fineTypes';
import type { UserDto } from './user';

export type CreateGroupDto = {
	name: string;
};

export type GroupDto = {
	groupId: number;
	name: string;
};

export type GroupDetailDto = {
	groupId: number;
	name: string;
	fineTypes: FineTypeDto[];
	admins: UserDto[];
	members: UserDto[];
	pendingMembers: UserDto[];
};
