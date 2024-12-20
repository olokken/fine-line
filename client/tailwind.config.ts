import { join } from 'path';
import type { Config } from 'tailwindcss';

import { skeleton } from '@skeletonlabs/tw-plugin';

const config = {
	darkMode: 'class',
	content: [
		'./src/**/*.{html,js,svelte,ts}',
		join(require.resolve('@skeletonlabs/skeleton'), '../**/*.{html,js,svelte,ts}')
	],
	theme: {
		extend: {
			height: {
				header: '6rem',
				sidebar: 'calc(100vh - 6rem)',
				content: 'calc(100vh - 6rem)'
			},
			width: {
				sidebar: '14rem',
				content: 'calc(100vw - 14rem)'
			}
		}
	},
	plugins: [
		skeleton({
			themes: { preset: ['modern'] }
		})
	]
} satisfies Config;

export default config;
