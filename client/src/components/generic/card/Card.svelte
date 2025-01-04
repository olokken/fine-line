<script lang="ts">
	import type { Snippet } from 'svelte';

	export let children: Snippet;
	export let className: string = '';
</script>

<div class={`card card-hover flex p-4 ${className} items-center`}>
	{@render children()}
</div>
