import { SvelteKitAuth } from '@auth/sveltekit';
import Keycloak from '@auth/sveltekit/providers/keycloak';

export const { handle, signIn, signOut } = SvelteKitAuth({
	providers: [Keycloak],
	callbacks: {
		async jwt({ token, account }) {
			if (account) {
				token.accessToken = account.access_token;
			}
			return token;
		},
		async session({ session, token }) {
			session.sessionToken = token.accessToken as string;
			return session;
		}
	}
});
