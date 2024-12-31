<script lang="ts">
	import { page } from '$app/stores';
	import { TabAnchor, TabGroup } from '@skeletonlabs/skeleton';
	import type { Snippet } from 'svelte';

	let { children }: { children: Snippet } = $props();
</script>

<TabGroup justify="justify-center mb-8"
	><TabAnchor href="/groups/browse" selected={$page.url.pathname === '/groups/browse'}
		>Browse groups</TabAnchor
	>
	<TabAnchor href="/groups/browse/create" selected={$page.url.pathname === '/groups/browse/create'}
		>Create new group</TabAnchor
	>
</TabGroup>
{@render children()}
