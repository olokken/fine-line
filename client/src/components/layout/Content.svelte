<script>
	import { isSideBarOpen } from '$lib/stores/layout';

	$: sidebarClass = $isSideBarOpen ? 'w-hidden sm:w-content' : 'w-full';
</script>

<main class={`h-content ${sidebarClass}`}><slot></slot></main>
