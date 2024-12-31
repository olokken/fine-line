export type RequestMembershipDto = {
	userId: string;
	groupId: number;
};

export type Membership = {
	userId: string;
	groupId: number;
	isAdmin: boolean;
};