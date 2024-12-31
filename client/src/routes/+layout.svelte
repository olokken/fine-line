<script lang="ts">
	import type { Snippet } from 'svelte';
	import '../app.css';
	import type { LayoutData } from './$types';
	import AppBar from '../components/layout/AppBar.svelte';
	import Sidebar from '../components/layout/SideBar.svelte';
	import Body from '../components/layout/Content.svelte';
	import { signIn, signOut } from '@auth/sveltekit/client';
	import { browser } from '$app/environment';
	import { initializeStores, Toast } from '@skeletonlabs/skeleton';
	import { userId, userName } from '$lib/stores/user';

	let { children, data }: { data: LayoutData; children: Snippet } = $props();
	initializeStores();

	userId.update(() => data.userId ?? '');
	userName.update(() => data.name ?? '');

	if (browser) {
		if (!data.session) signIn();
		//@ts-ignore
		if (!data.session.sessionToken) {
			signOut();
		}
	}
</script>

<AppBar />
<div class="flex">
	<Sidebar groups={data.groups ?? []} />

	<Body>
		{@render children()}
	</Body>
</div>

<Toast />
