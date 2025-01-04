import type { GroupDetailDto } from '$lib/models/group';
import { writable } from 'svelte/store';

export const group = writable<GroupDetailDto>();
