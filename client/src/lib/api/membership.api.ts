import type {
	Membership,
	RequestMembershipDto,
	UpdateRequestedMembershipDto
} from '$lib/models/membership';
import type { Session } from '@auth/sveltekit';
import { fetchDataFromClient } from './fetcher';
import { invalidateAll } from '$app/navigation';

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

const handleMembershipRequest = async (
	session: Nullable<Session>,
	updateMembershipDto: UpdateRequestedMembershipDto
) => {
	const response = await fetchDataFromClient<Membership, RequestMembershipDto>(
		session,
		'/api/v1/membership',
		'PUT',
		updateMembershipDto
	);
	await invalidateAll();
	return response;
};

export const MembershipApi = { requestMembership, handleMembershipRequest };
