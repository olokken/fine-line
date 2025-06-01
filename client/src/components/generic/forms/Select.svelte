<script lang="ts">
	export let label: string;
	export let name: string;
	export let options: { value: string; label: string }[] = [];
</script>

<label class="label text-lg">
	<span class="ml-2">{label}</span>
	<select {name} class="select h-16 p-4">
		{#each options as option}
			<option value={option.value}>{option.label}</option>
		{/each}
	</select>
</label>
