<script lang="ts">
	import { AppRail } from '@skeletonlabs/skeleton';
	import { isSideBarOpen } from '$lib/stores/layout';
	import { fly } from 'svelte/transition';
	import type { GroupDto } from '$lib/models/group';
	import GroupTab from './GroupTab.svelte';

	export let groups: GroupDto[];
</script>

{#if $isSideBarOpen}
	<aside class="h-sidebar w-full sm:w-sidebar" transition:fly={{ x: -200, duration: 500 }}>
		<AppRail class="w-full">
			<a
				class="variant-filled btn btn-lg mb-4 mt-4 w-full"
				slot="lead"
				type="button"
				href="/groups/browse">Create/Join Group</a
			>

			<ul>
				<h3 class="h3 mb-4 text-center">Your groups</h3>
				{#each groups as group}
					<GroupTab groupId={group.groupId} groupName={group.name} />
				{/each}
			</ul>
		</AppRail>
	</aside>
{/if}
