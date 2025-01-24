<script lang="ts">
	import { MembershipApi } from '$lib/api/membership.api';
	import { group } from '$lib/stores/group';
	import { getToastFromResponse } from '$lib/utils/request.utils';
	import { getToastStore } from '@skeletonlabs/skeleton';
	import UserCard from '../../../../components/generic/card/UserCard.svelte';
	import type { PageData } from './$types';

	let { data }: { data: PageData } = $props();

	const toastStore = getToastStore();

	const onAddUser = async (userId: string) => {
		const response = await MembershipApi.handleMembershipRequest(data.session, {
			userId: userId,
			groupId: $group.groupId,
			accepted: true
		});

		const toastResponse = getToastFromResponse(response, 'Successfully accepted user');
		if (toastResponse) toastStore.trigger(toastResponse);
	};

	const onDeclineUser = async (userId: string) => {
		const response = await MembershipApi.handleMembershipRequest(data.session, {
			userId: userId,
			groupId: $group.groupId,
			accepted: false
		});

		const toastResponse = getToastFromResponse(response, 'Successfully declined user');
		if (toastResponse) toastStore.trigger(toastResponse);
	};
</script>

<div class="flex flex-col gap-4">
	<h2 class="h2">Members</h2>
	{#if $group.pendingMembers.length > 0}
		<ul class="list">
			<h2 class="h2">Membership requests</h2>
			{#each $group.pendingMembers as pendingMember}
				<li class="card card-hover w-full">
					<UserCard
						name={pendingMember.name}
						userType="RequestedMember"
						upgradeUser={() => onAddUser(pendingMember.userId)}
						downgradeUser={() => onDeclineUser(pendingMember.userId)}
					/>
				</li>
			{/each}
		</ul>
	{/if}

	<h3 class="h3">Admins</h3>
	<ul class="list">
		{#each $group.admins as admin}
			<li class="card card-hover w-full">
				<UserCard name={admin.name} userType="Admin" />
			</li>
		{/each}
	</ul>

	<h3 class="h3">Members</h3>
	<ul class="list">
		{#each $group.members as member}
			<li class="card card-hover w-full">
				<UserCard name={member.name} userType="Member" />
			</li>
		{/each}
	</ul>
</div>
