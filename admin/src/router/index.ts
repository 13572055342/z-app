import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const Dashboard = () => import('@/views/Dashboard.vue')
const Templates = () => import('@/views/Templates.vue')
const Submissions = () => import('@/views/Submissions.vue')
const QRCodes = () => import('@/views/QRCodes.vue')

const routes: RouteRecordRaw[] = [
	{ path: '/', component: Dashboard },
	{ path: '/templates', component: Templates },
	{ path: '/submissions', component: Submissions },
	{ path: '/qrcodes', component: QRCodes },
]

const router = createRouter({
	history: createWebHistory(),
	routes,
})

export default router
