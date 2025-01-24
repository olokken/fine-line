<script lang="ts">
	import { getToastFromResponse } from '$lib/utils/request.utils';
	import FormInput from '../../../../../components/generic/forms/FormInput.svelte';
	import TextArea from '../../../../../components/generic/forms/TextArea.svelte';
	import type { ActionData } from './$types';
	import { group } from '$lib/stores/group';
	import { getToastStore } from '@skeletonlabs/skeleton';

	const toastStore = getToastStore();

	let { form }: { form: ActionData } = $props();

	const toastResponse = getToastFromResponse(form, 'Created new fine type');
	if (toastResponse) toastStore.trigger(toastResponse);
</script>

<div class="center-align flex w-full flex-col items-center">
	<form class="flex w-full flex-col justify-center gap-2 md:w-[500px]" method="POST">
		<h1 class="h1">Create new fine type</h1>

		<input type="hidden" name="groupId" value={$group?.groupId} />

		<FormInput name="name" label="Name"></FormInput>
		<TextArea name="description" label="Description"></TextArea>
		<FormInput name="sum" label="Sum" type="number"></FormInput>
		<button class="variant-filled btn w-full md:w-fit">Create fine type</button>
	</form>
</div>
