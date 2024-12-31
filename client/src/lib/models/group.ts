import type { User } from '@auth/sveltekit';

export type CreateGroupDto = {
	name: string;
};

export type Group = {
	groupId: number;
	name: string;
	admins: User[];
};

export type GroupDto = {
	groupId: number;
	name: string;
};
