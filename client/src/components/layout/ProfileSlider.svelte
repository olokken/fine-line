<script lang="ts">
	import { Drawer } from '@skeletonlabs/skeleton';
	import { signOut } from '@auth/sveltekit/client';
</script>

<Drawer position="right">
	<div class="flex w-full flex-col justify-center gap-4 p-4">
		<h1 class="h1">Profile</h1>
		<button class="variant-filled-primary btn" onclick={() => signOut()}>Sign Out</button>
	</div>
</Drawer>
