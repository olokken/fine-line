<script lang="ts">
	import MenyCard from '../../../components/generic/card/MenyCard.svelte';
	import { BadgeDollarSign, Activity, UsersRound, Ticket, Settings, Trophy } from 'lucide-svelte';
	import { page } from '$app/stores';
	import { isGroupAdmin } from '$lib/stores/group';

	const size = 80;
	let basePath = $page.url.pathname;
</script>

<div class="grid grid-cols-2 gap-4">
	{#if $isGroupAdmin}
		<MenyCard title="Issue fines" href={`${basePath}/fine`}><BadgeDollarSign {size} /></MenyCard>
	{/if}

	<MenyCard title="Leaderboard" href=""><Trophy {size} /></MenyCard>
	<MenyCard title="Activity" href=""><Activity {size} /></MenyCard>
	<MenyCard title="Fine types" href={`${basePath}/fineTypes`}><Ticket {size} /></MenyCard>
	<MenyCard title="Members" href={`${basePath}/members`}><UsersRound {size} /></MenyCard>

	{#if $isGroupAdmin}
		<MenyCard title="Settings" href=""><Settings {size} /></MenyCard>
	{/if}
</div>
