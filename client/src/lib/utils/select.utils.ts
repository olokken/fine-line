export const createToOption =
	<T>(valueKey: keyof T, labelKey: keyof T) =>
	(item: T): { value: string; label: string } => ({
		value: String(item[valueKey]),
		label: String(item[labelKey])
	});
