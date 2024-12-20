<script lang="ts">
	import type { Snippet } from 'svelte';
	import '../app.css';
	import type { LayoutData } from './$types';
	import { signInIfNotAuthenticated } from '$lib/auth/auth';
	import { browser } from '$app/environment';
	import { tokenCookieName } from '$lib/utils/constants';
	import AppBar from '../components/layout/AppBar.svelte';
	import Sidebar from '../components/layout/SideBar.svelte';
	import Body from '../components/layout/Content.svelte';

	let { children }: { data: LayoutData; children: Snippet } = $props();

	async function handleAuth() {
		const token = await signInIfNotAuthenticated();

		if (token) {
			document.cookie = `${tokenCookieName}=${token}; path=/; secure; SameSite=Strict`;
		}
	}

	if (browser) {
		handleAuth();
	}
</script>

<AppBar />
<div class="flex">
	<Sidebar />

	<Body>
		{@render children()}
	</Body>
</div>
