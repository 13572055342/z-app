import { defineConfig } from 'vite'
import Uni from '@dcloudio/vite-plugin-uni'

// Support both default-exported function and pre-built plugin object/array
const resolveUniPlugins = () => {
	const maybeFn: any = Uni as any
	if (typeof maybeFn === 'function') {
		const res = maybeFn()
		return Array.isArray(res) ? res : [res]
	}
	return Array.isArray(Uni) ? (Uni as any[]) : [Uni as any]
}

export default defineConfig({
	plugins: resolveUniPlugins(),
})
