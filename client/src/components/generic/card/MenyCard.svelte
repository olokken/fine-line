<script lang="ts">
	import Card from './Card.svelte';
	import type { Snippet } from 'svelte';

	export let title: string;
	export let href: string;
	export let children: Snippet;
</script>

<a class="flex w-full" {href}>
	<Card className="h-48 w-full flex flex-col justify-center items-center gap-4">
		{@render children()}
		<h3 class="h3">{title}</h3>
	</Card>
</a>
