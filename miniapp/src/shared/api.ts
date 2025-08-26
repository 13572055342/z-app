export const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080/api'

export async function apiGet<T = any>(path: string, params?: Record<string, any>): Promise<T> {
	const url = `${API_BASE}${path}`
	const [err, res] = await uni.request({ url, method: 'GET', data: params })
	return (res?.data as T)
}

export async function apiPost<T = any>(path: string, data?: any): Promise<T> {
	const url = `${API_BASE}${path}`
	const [err, res] = await uni.request({ url, method: 'POST', data })
	return (res?.data as T)
}
