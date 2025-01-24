import type { GroupDetailDto } from '$lib/models/group';

export const checkIfUserIsAdmin = (group: GroupDetailDto, userId: string): boolean => {
	return group.admins.some((user) => user.userId == userId);
};
