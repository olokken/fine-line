<script lang="ts">
	import { group } from '$lib/stores/group';
	import UserCard from '../../../../components/generic/card/UserCard.svelte';

	const onAddUser = () => {
		console.log('Godkjenner');
	};

	const onDeclineUser = () => {
		console.log('Avviser');
	};
</script>

<div class="flex flex-col gap-4">
	<h1 class="h1">Members</h1>

	{#if $group.pendingMembers.length > 0}
		<ul class="list">
			<h2 class="h2">Membership requests</h2>
			{#each $group.pendingMembers as pendingMember}
				<li class="card card-hover w-full">
					<UserCard
						name={pendingMember.name}
						userType="RequestedMember"
						upgradeUser={onAddUser}
						downgradeUser={onDeclineUser}
					/>
				</li>
			{/each}
		</ul>
	{/if}

	<h2 class="h2">Admins</h2>
	<ul class="list">
		{#each $group.admins as admin}
			<li class="card card-hover w-full">
				<UserCard name={admin.name} userType="Admin" />
			</li>
		{/each}
	</ul>

	<h2 class="h2">Members</h2>
	<ul class="list">
		{#each $group.members as member}
			<li class="card card-hover w-full">
				<UserCard name={member.name} userType="Member" />
			</li>
		{/each}
	</ul>
</div>
