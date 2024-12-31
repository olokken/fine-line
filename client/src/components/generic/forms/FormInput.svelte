<script lang="ts">
	import type { HTMLInputTypeAttribute } from 'svelte/elements';

	export let label: string;
	export let placeHolder: string = label;
	export let type: HTMLInputTypeAttribute = 'text';
	export let name: string;
</script>

<label class="label text-lg">
	<span class="ml-2">{label}</span>
	<input {name} class="input h-16 p-4" {type} placeholder={placeHolder} />
</label>
