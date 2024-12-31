<script>
	import { isSideBarOpen } from '$lib/stores/layout';

	$: sidebarClass = $isSideBarOpen ? 'sm:w-content max-sm:hidden' : 'w-full';
</script>

<main class={`h-content ${sidebarClass} p-6`}>
	<slot></slot>
</main>
