<script lang="ts">
	import { group } from '$lib/stores/group';
	import { Accordion, AccordionItem } from '@skeletonlabs/skeleton';
	import { Ticket } from 'lucide-svelte';
</script>

<div class="mb-2 flex justify-between">
	<h2 class="h2">Fine types</h2>
	<a href="fineTypes/create" class="variant-filled btn">Create new fine type</a>
</div>
<ul>
	<Accordion>
		{#each $group.fineTypes as fineType}
			<li>
				<AccordionItem>
					<svelte:fragment slot="lead"><Ticket /></svelte:fragment>

					<svelte:fragment slot="summary"
						><div class="flex justify-between text-lg">
							<p>{fineType.name}</p>
							<p>Sum: {fineType.sum}kr</p>
						</div>
					</svelte:fragment>

					<svelte:fragment slot="content">{fineType.description}</svelte:fragment>
				</AccordionItem>
			</li>
		{/each}
	</Accordion>
</ul>
