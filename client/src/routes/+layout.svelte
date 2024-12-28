<script lang="ts">
	import type { Snippet } from 'svelte';
	import '../app.css';
	import type { LayoutData } from './$types';
	import AppBar from '../components/layout/AppBar.svelte';
	import Sidebar from '../components/layout/SideBar.svelte';
	import Body from '../components/layout/Content.svelte';
	import { signIn, signOut } from '@auth/sveltekit/client';
	import { browser } from '$app/environment';
	import { initializeStores } from '@skeletonlabs/skeleton';

	let { children, data }: { data: LayoutData; children: Snippet } = $props();
	initializeStores();

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
	<Sidebar />

	<Body>
		{@render children()}
	</Body>
</div>
