import type { Membership, RequestMembershipDto } from '$lib/models/membership';
import type { Session } from '@auth/sveltekit';
import { fetchDataFromClient } from './fetcher';

const requestMembership = async (
	session: Nullable<Session>,
	requestMembershipDto: RequestMembershipDto
) => {
	return await fetchDataFromClient<Membership, RequestMembershipDto>(
		session,
		'/api/v1/membership',
		'POST',
		requestMembershipDto
	);
};

export const MembershipApi = { requestMembership };
