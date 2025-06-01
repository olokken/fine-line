<script lang="ts">
	import { getToastFromResponse } from '$lib/utils/request.utils';
	import type { ActionData } from './$types';
	import { group } from '$lib/stores/group';
	import { getToastStore } from '@skeletonlabs/skeleton';
	import FormInput from '../../../../components/generic/forms/FormInput.svelte';
	import TextArea from '../../../../components/generic/forms/TextArea.svelte';
	import Select from '../../../../components/generic/forms/Select.svelte';
	import { createToOption } from '$lib/utils/select.utils';
	import { derived } from 'svelte/store';

	const toastStore = getToastStore();

	let { form }: { form: ActionData } = $props();

	const toastResponse = getToastFromResponse(form, 'Fine issued');
	if (toastResponse) toastStore.trigger(toastResponse);

	const userOptions = derived(group, ($group) => {
		const members = $group?.members ?? [];
		const admins = $group?.admins ?? [];
		const allUsers = [...members, ...admins];
		return allUsers.map(createToOption('userId', 'name'));
	});

	const fineTypeOptions = derived(group, ($group) => {
		return $group.fineTypes.map(createToOption('fineTypeId', 'name'));
	});
</script>

<div class="center-align flex w-full flex-col items-center">
	<form class="flex w-full flex-col justify-center gap-2 md:w-[500px]" method="POST">
		<h1 class="h1">Issue fine</h1>

		<input type="hidden" name="groupId" value={$group?.groupId} />
		<Select name="recieverId" label="Reciever" options={$userOptions}></Select>
		<Select name="fineTypeId" label="Fine type" options={$fineTypeOptions}></Select>
		<button class="variant-filled btn w-full md:w-fit">Issue fine</button>
	</form>
</div>
