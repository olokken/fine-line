<script lang="ts">
	import { Avatar, AppBar as SkeletonAppBar } from '@skeletonlabs/skeleton';
	import SideBarButton from '../generic/buttons/SideBarButton.svelte';
	import { UserRoundPen } from 'lucide-svelte';
	import { isSideBarOpen } from '$lib/stores/layout';
	import { getDrawerStore } from '@skeletonlabs/skeleton';
	import type { DrawerSettings } from '@skeletonlabs/skeleton';
	import ProfileSlider from './ProfileSlider.svelte';

	const drawerStore = getDrawerStore();

	const toggleSideBar = () => {
		isSideBarOpen.update((currentValue) => !currentValue);
	};

	export const drawerSettings: DrawerSettings = {
		bgDrawer: 'bg-tertiary-500 text-white',
		bgBackdrop: 'bg-gradient-to-tr from-secondary-500/50 via-secondary-500/50 to-tertiary-500/50',
		width: 'w-[280px] md:w-[480px]',
		padding: 'p-4',
		rounded: 'rounded-xl'
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
			onclick={() => drawerStore.open(drawerSettings)}
			slot="trail"
			border="border-4 border-surface-300-600-token hover:!border-primary-500"
			cursor="cursor-pointer"><UserRoundPen /></Avatar
		>
		<ProfileSlider />
	</SkeletonAppBar>
</header>
