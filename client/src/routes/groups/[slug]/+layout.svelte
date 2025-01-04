<script lang="ts">
	import type { Snippet } from 'svelte';
	import type { LayoutData } from './$types';
	import { group } from '$lib/stores/group';
	import GoBackButton from '../../../components/generic/buttons/GoBackButton.svelte';
	import { page } from '$app/stores';

	let { children, data }: { data: LayoutData; children: Snippet } = $props();

	group.set(data.group);
	$effect(() => group.set(data.group));

	const showBackButton = $derived.by(() => {
		let basePath: string = $page.url.pathname;
		const splits = basePath.split('/');
		return splits.length > 3;
	});
</script>

{#if showBackButton}
	<GoBackButton />
{/if}

{@render children()}
