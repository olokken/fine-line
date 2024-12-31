<script>
	import { page } from '$app/stores';

	export let groupId;
	export let groupName;

	const href = `/groups/${groupId}`;

	$: isActive = $page.url.pathname === href;
</script>

<li class="h-12 w-full cursor-pointer">
	<a
		{href}
		class={`flex h-full w-full items-center justify-center hover:bg-primary-100 ${isActive ? 'bg-primary-200' : ''}`}
		>{groupName}</a
	>
</li>
