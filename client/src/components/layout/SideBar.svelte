<script lang="ts">
	import { AppRail, AppRailAnchor, AppRailTile } from '@skeletonlabs/skeleton';
	import { isSideBarOpen } from '$lib/stores/layout';
	import { fly } from 'svelte/transition';
	let currentTile: number = 0;
</script>

{#if $isSideBarOpen}
	<aside class="h-sidebar sm:w-sidebar w-full" transition:fly={{ x: -200, duration: 500 }}>
		<AppRail class="w-full">
			<button class="variant-filled btn btn-lg mb-4 mt-4 w-full" slot="lead" type="button"
				>Create/Join Group</button
			>
		</AppRail>
	</aside>
{/if}
