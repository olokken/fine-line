<script lang="ts">
	import { getToastFromResponse } from '$lib/utils/request.utils';
	import FormInput from '../../../../components/generic/forms/FormInput.svelte';
	import type { ActionData } from './$types';
	import { getToastStore } from '@skeletonlabs/skeleton';

	const toastStore = getToastStore();

	let { form }: { form: ActionData } = $props();

	const toastResponse = getToastFromResponse(form, 'Created new group');
	if (toastResponse) toastStore.trigger(toastResponse);
</script>

<div class="center-align flex w-full flex-col items-center">
	<form class="flex w-full flex-col justify-center gap-2 md:w-[500px]" method="POST">
		<h1 class="h1">Create new group</h1>
		<FormInput name="name" label="Name"></FormInput>
		<button class="variant-filled btn w-full md:w-fit">Create group</button>
	</form>
</div>
