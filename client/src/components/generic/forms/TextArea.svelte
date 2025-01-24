<script lang="ts">
	export let label: string;
	export let placeHolder: string = label;
	export let name: string;
	export let rows: number = 4;
</script>

<label class="label text-lg">
	<span class="ml-2">{label}</span>
	<textarea {name} class="textarea p-4" placeholder={placeHolder} {rows}></textarea>
</label>
