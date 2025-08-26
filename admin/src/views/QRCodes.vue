<template>
	<el-card>
		<template #header>
			<el-space>
				<el-input v-model.number="form.template_id" placeholder="模板ID" style="width: 180px" />
				<el-button type="primary" @click="create">生成二维码</el-button>
				<el-button @click="fetchList">刷新</el-button>
			</el-space>
		</template>
		<el-table :data="list" style="width: 100%">
			<el-table-column prop="id" label="#" width="80" />
			<el-table-column prop="template_id" label="模板ID" width="120" />
			<el-table-column prop="code" label="Code" />
			<el-table-column prop="is_active" label="状态" width="100">
				<template #default="{ row }"><el-tag :type="row.is_active?'success':'danger'">{{ row.is_active ? '启用' : '禁用' }}</el-tag></template>
			</el-table-column>
			<el-table-column label="操作" width="220">
				<template #default="{ row }">
					<el-button link @click="toggle(row)">{{ row.is_active?'禁用':'启用' }}</el-button>
					<el-popconfirm title="确定删除?" @confirm="remove(row)">
						<template #reference>
							<el-button link type="danger">删除</el-button>
						</template>
					</el-popconfirm>
				</template>
			</el-table-column>
		</el-table>
	</el-card>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { http } from '@/shared/api'

const list = ref<any[]>([])
const form = ref<{ template_id: number | null }>({ template_id: null })

async function fetchList() {
	const res = await http.get('/qrcodes')
	list.value = res.data
}

async function create() {
	if (!form.value.template_id) return
	await http.post('/qrcodes', { template_id: form.value.template_id, code: '', is_active: true })
	await fetchList()
}

async function toggle(row: any) {
	await http.patch(`/qrcodes/${row.id}`, { is_active: !row.is_active })
	await fetchList()
}

async function remove(row: any) {
	await http.delete(`/qrcodes/${row.id}`)
	await fetchList()
}

onMounted(fetchList)
</script>
