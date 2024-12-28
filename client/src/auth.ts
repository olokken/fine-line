import { SvelteKitAuth } from '@auth/sveltekit';
import Keycloak from '@auth/sveltekit/providers/keycloak';

const isTokenExpired = (exp: number | undefined) => {
	if (!exp) return true;
	const currentTimeInSeconds = Math.floor(Date.now() / 1000);
	return exp < currentTimeInSeconds - 15;
};

export const { handle, signIn, signOut } = SvelteKitAuth({
	providers: [Keycloak],
	callbacks: {
		async jwt({ token, account }) {
			if (account) {
				token.accessToken = account.access_token;
				token.expiresAt = account.expires_at;
			}

			return token;
		},
		async session({ session, token }) {
			const isExpired = isTokenExpired(token.expiresAt as number | undefined);
			session.sessionToken = token.accessToken as string;
			if (isExpired) {
				session.sessionToken = '';
			}
			return session;
		}
	}
});
