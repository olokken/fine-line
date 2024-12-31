<script lang="ts">
	import { Search } from 'lucide-svelte';
	export let onChange = (text: string) => {};
	export let useSubmitButton: bool = false;
	export let onSearchSubmit = (text: string) => {};
	export let className: string = '';
	let searchText: string = '';
</script>

<div class={className}>
	<p class="ml-2 text-lg">Search</p>
	<div class="input-group input-group-divider h-16 grid-cols-[auto_1fr_auto] text-lg">
		<div class="input-group-shim"><Search></Search></div>
		<input
			type="search"
			class="border-none pl-4 outline-none"
			placeholder="Search..."
			bind:value={searchText}
			oninput={() => onChange(searchText)}
		/>
		{#if useSubmitButton}
			<button class="variant-filled-secondary" onclick={() => onSearchSubmit(searchText)}
				>Submit</button
			>
		{/if}
	</div>
</div>
