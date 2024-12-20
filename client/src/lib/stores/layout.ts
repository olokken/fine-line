import { writable } from 'svelte/store';

export const isSideBarOpen = writable<boolean>(false);
