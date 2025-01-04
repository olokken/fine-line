<script lang="ts">
	import { MoveLeft } from 'lucide-svelte';
	const goBack = () => {
		history.back();
	};
</script>

<button class="variant-filled-primary btn mb-4" onclick={goBack}>
	<MoveLeft class="mr-2" /> Go Back
</button>
