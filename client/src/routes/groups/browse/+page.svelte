<script lang="ts">
	import Card from '../../../components/generic/card/Card.svelte';
	import SearchBar from '../../../components/generic/search/SearchBar.svelte';
	import type { PageData } from '../$types';
	import { Group } from 'lucide-svelte';
	import { userId } from '$lib/stores/user';
	import type { RequestMembershipDto } from '$lib/models/membership';
	import { get } from 'svelte/store';
	import type { ActionData } from './$types';
	import { fetchDataFromClient } from '$lib/api/fetcher';
	import { GroupApi } from '$lib/api/group.api';
	import { MembershipApi } from '$lib/api/membership.api';

	let { data }: { data: PageData } = $props();

	const onSearchTextChange = (text: string) => {
		searchText = text;
	};

	let searchText = $derived('');
	const groups = $derived(
		data.groups.filter((group) => group.name.toLowerCase().startsWith(searchText.toLowerCase()))
	);

	const onRequestToJoin = async (groupId: number) => {
		const id = get(userId);
		const response = await MembershipApi.requestMembership(data.session, {
			userId: id,
			groupId: groupId
		});

		console.log(response);
	};
</script>

<div class="mb-4 flex w-full justify-center">
	<SearchBar onChange={onSearchTextChange} className="md:w-[350px] max-md:w-full"></SearchBar>
</div>

<ul class="grid grid-cols-[repeat(auto-fit,_minmax(250px,_1fr))] gap-2">
	{#each groups as group}
		<li class="flex justify-center">
			<Card className="w-[250px] gap-2">
				<Group size={80}></Group>
				<h3 class="h3">{group.name}</h3>
				<button
					class="variant-filled-primary btn"
					onclick={(event) => onRequestToJoin(group.groupId)}>Request to join</button
				>
			</Card>
		</li>
	{/each}
</ul>
