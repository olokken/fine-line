<script lang="ts">
	import MenyCard from '../../../components/generic/card/MenyCard.svelte';
	import { BadgeDollarSign, Activity, UsersRound, Ticket, Settings } from 'lucide-svelte';
	import { group } from '$lib/stores/group';
	import { page } from '$app/stores';

	const size = 80;
	let basePath = $page.url.pathname;
</script>

<h1 class="h1 mb-4 ml-2">{$group.name}</h1>
<div class="grid grid-cols-2 gap-4">
	<MenyCard title="Hand out fines" href=""><BadgeDollarSign {size} /></MenyCard>
	<MenyCard title="Activity" href=""><Activity {size} /></MenyCard>
	<MenyCard title="Members" href={`${basePath}/members`}><UsersRound {size} /></MenyCard>
	<MenyCard title="Fine types" href=""><Ticket {size} /></MenyCard>
	<MenyCard title="Settings" href=""><Settings {size} /></MenyCard>
</div>
