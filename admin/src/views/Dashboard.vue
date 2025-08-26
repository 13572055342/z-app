<template>
	<el-row :gutter="16">
		<el-col :span="6"><el-card><div>模板总数</div><h2>{{ stats.templates }}</h2></el-card></el-col>
		<el-col :span="6"><el-card><div>提交总数</div><h2>{{ stats.submissions }}</h2></el-card></el-col>
		<el-col :span="6"><el-card><div>二维码总数</div><h2>{{ stats.qrcodes }}</h2></el-card></el-col>
	</el-row>
</template>

<script setup lang="ts">
import { reactive, onMounted } from 'vue'
import { http } from '@/shared/api'

const stats = reactive({ templates: 0, submissions: 0, qrcodes: 0 })

async function fetchStats() {
	const res = await http.get('/stats/overview')
	Object.assign(stats, res.data)
}

onMounted(fetchStats)
</script>
