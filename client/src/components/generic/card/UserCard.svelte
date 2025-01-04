<script lang="ts">
	import Card from './Card.svelte';
	import { Lock, UserPlus, User, Plus, X } from 'lucide-svelte';

	type UserType = 'RequestedMember' | 'Admin' | 'Member';

	export let name: string;
	export let userType: UserType;
	export let upgradeUser = () => {};
	export let downgradeUser = () => {};

	const size = 32;
</script>

<Card className="h-16 flex items-center space-x-2 w-full justify-between">
	<div class="flex gap-2">
		{#if userType === 'Admin'}
			<Lock {size} />
		{:else if userType === 'RequestedMember'}
			<UserPlus {size} />
		{:else if userType === 'Member'}
			<User {size} />
		{/if}
		<p class="text-xl">{name}</p>
	</div>

	<div>
		{#if userType === 'RequestedMember'}
			<button class="variant-filled btn-icon" onclick={upgradeUser}><Plus /></button>
			<button class="variant-filled btn-icon" onclick={downgradeUser}><X /></button>
		{/if}
	</div>
</Card>
