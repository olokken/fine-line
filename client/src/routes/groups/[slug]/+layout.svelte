<script lang="ts">
	import type { Snippet } from 'svelte';
	import type { LayoutData } from './$types';
	import { group, isGroupAdmin } from '$lib/stores/group';
	import GoBackButton from '../../../components/generic/buttons/GoBackButton.svelte';
	import { page } from '$app/stores';
	import { userId } from '$lib/stores/user';
	import { checkIfUserIsAdmin } from '$lib/utils/admin.utils';

	let { children, data }: { data: LayoutData; children: Snippet } = $props();

	group.set(data.group);
	isGroupAdmin.set(checkIfUserIsAdmin(data.group, $userId));

	$effect(() => {
		group.set(data.group);
		isGroupAdmin.set(checkIfUserIsAdmin(data.group, $userId));
	});

	const showBackButton = $derived.by(() => {
		let basePath: string = $page.url.pathname;
		const splits = basePath.split('/');
		return splits.length > 3;
	});
</script>

<h1 class="h1 mb-4 ml-2">{$group.name}</h1>
{#if showBackButton}
	<GoBackButton />
{/if}

{@render children()}
