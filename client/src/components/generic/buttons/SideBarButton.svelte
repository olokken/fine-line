<script lang="ts">
	import { IndentIncrease, IndentDecrease } from 'lucide-svelte';
	import { isSideBarOpen } from '$lib/stores/layout';

	export let size: number = 42;
	export let onClick: () => void;
</script>

<button type="button" on:click={onClick} class="btn p-2 hover:bg-primary-50">
	{#if $isSideBarOpen}
		<IndentIncrease {size} />
	{:else}
		<IndentDecrease {size} />
	{/if}
</button>
