import type { GroupDto } from './group';

export type User = {
	userId: string;
	name: string;
	groups: GroupDto[];
};

export type UserDto = {
	userId: string;
	name: string;
};
