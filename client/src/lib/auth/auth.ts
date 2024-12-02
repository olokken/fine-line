import { tokenCookieName } from '$lib/utils/constants';
import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
	url: import.meta.env.VITE_KEYCLOAK_URL,
	realm: import.meta.env.VITE_KEYCLOAK_REALM,
	clientId: import.meta.env.VITE_KEYCLOAK_ID
});

export const signInIfNotAuthenticated = async () => {
	const isAuth = await keycloak.init({
		onLoad: 'login-required',
		pkceMethod: 'S256',
		flow: 'standard',
		useNonce: true
	});

	if (isAuth && keycloak.token) {
		return keycloak.token;
	}

	await keycloak.logout();
};

export const signOut = async () => {
	document.cookie = `${tokenCookieName}=; path=/; expires=Thu, 01 Jan 1970 00:00:00 UTC;`;
	return await keycloak.logout();
};
