<script>
	import { Avatar, AppBar as SkeletonAppBar } from '@skeletonlabs/skeleton';
	import SideBarButton from '../generic/buttons/SideBarButton.svelte';
	import { UserRoundPen } from 'lucide-svelte';
	import { isSideBarOpen } from '$lib/stores/layout';

	const toggleSideBar = () => {
		isSideBarOpen.update((currentValue) => !currentValue);
	};
</script>

<header class="h-header">
	<SkeletonAppBar
		gridColumns="grid-cols-3"
		slotDefault="place-self-center"
		slotTrail="place-content-end"
	>
		<SideBarButton onClick={toggleSideBar} slot="lead" />
		<h2 class="h2">FineLine</h2>
		<Avatar
			slot="trail"
			border="border-4 border-surface-300-600-token hover:!border-primary-500"
			cursor="cursor-pointer"><UserRoundPen /></Avatar
		>
	</SkeletonAppBar>
</header>
