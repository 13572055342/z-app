<template>
	<el-card>
		<template #header>
			<el-space>
				<el-input v-model="templateId" placeholder="模板ID筛选" style="width: 200px" />
				<el-button @click="fetchList">查询</el-button>
			</el-space>
		</template>
		<el-table :data="list" @row-dblclick="openDetail" style="width: 100%">
			<el-table-column prop="id" label="#" width="80" />
			<el-table-column prop="template_id" label="模板ID" width="120" />
			<el-table-column prop="created_at" label="提交时间" width="200" />
			<el-table-column label="操作" width="150">
				<template #default="{ row }">
					<el-button link @click="openDetail(row)">详情</el-button>
				</template>
			</el-table-column>
		</el-table>
	</el-card>

	<el-drawer v-model="drawer" title="提交详情" size="40%">
		<pre style="white-space: pre-wrap">{{ pretty(detail) }}</pre>
	</el-drawer>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { http } from '@/shared/api'

const templateId = ref('')
const list = ref<any[]>([])
const drawer = ref(false)
const detail = ref<any>(null)

function pretty(v: any) {
	try { return JSON.stringify(JSON.parse(v?.data_json||'{}'), null, 2) } catch { return v?.data_json }
}

async function fetchList() {
	const params: any = {}
	if (templateId.value) params.template_id = Number(templateId.value)
	const res = await http.get('/submissions', { params })
	list.value = res.data
}

function openDetail(row: any) {
	detail.value = row
	drawer.value = true
}

onMounted(fetchList)
</script>
