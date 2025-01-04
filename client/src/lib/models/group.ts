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
	admins: UserDto[];
	members: UserDto[];
	pendingMembers: UserDto[];
};
