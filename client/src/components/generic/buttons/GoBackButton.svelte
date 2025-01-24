<script lang="ts">
	import { goto } from '$app/navigation';
	import { removeOneSegment } from '$lib/utils/paths.utils';
	import { MoveLeft } from 'lucide-svelte';

	const goBack = () => {
		const newPath = removeOneSegment(window.location.pathname);
		goto(newPath);
	};
</script>

<button class="variant-filled-primary btn mb-4" onclick={goBack}>
	<MoveLeft class="mr-2" /> Go Back
</button>
