export const removeOneSegment = (pathName: string): string => {
	const base = pathName.replace(/\/$/, '').split('/').slice(0, -1).join('/');
	return base.startsWith('/') ? base : '/' + base;
};
