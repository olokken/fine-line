<script lang="ts">
	import type { Snippet } from 'svelte';
	import '../app.css';
	import type { LayoutData } from './$types';
	import AppBar from '../components/layout/AppBar.svelte';
	import Sidebar from '../components/layout/SideBar.svelte';
	import Body from '../components/layout/Content.svelte';
	import { signIn } from "@auth/sveltekit/client"
	import { browser } from '$app/environment';
	
	let { children, data }: { data: LayoutData; children: Snippet } = $props();

	if(browser) {		
		if(!data.session) signIn()
	}

</script>

<AppBar />
<div class="flex">
	<Sidebar />

	<Body>
		{@render children()}
	</Body>
</div>
