<template>
	<el-card>
		<template #header>
			<el-space>
				<el-button type="primary" @click="openCreate">新建模板</el-button>
				<el-button @click="fetchList">刷新</el-button>
			</el-space>
		</template>
		<el-table :data="list" style="width: 100%">
			<el-table-column prop="id" label="#" width="80" />
			<el-table-column prop="name" label="名称" />
			<el-table-column prop="is_published" label="状态" width="100">
				<template #default="{ row }"><el-tag :type="row.is_published?'success':'info'">{{ row.is_published ? '已发布' : '未发布' }}</el-tag></template>
			</el-table-column>
			<el-table-column prop="created_at" label="创建时间" width="200" />
			<el-table-column label="操作" width="240">
				<template #default="{ row }">
					<el-button link @click="openEdit(row)">编辑</el-button>
					<el-button link type="warning" @click="togglePublish(row)">{{ row.is_published?'暂停':'发布' }}</el-button>
					<el-popconfirm title="确定删除?" @confirm="remove(row)">
						<template #reference>
							<el-button link type="danger">删除</el-button>
						</template>
					</el-popconfirm>
				</template>
			</el-table-column>
		</el-table>
	</el-card>

	<el-dialog v-model="dialogVisible" :title="editing? '编辑模板':'新建模板'" width="700px">
		<el-form :model="form" label-width="100px">
			<el-form-item label="名称"><el-input v-model="form.name" /></el-form-item>
			<el-form-item label="描述"><el-input v-model="form.description" /></el-form-item>
			<el-form-item label="Schema JSON">
				<el-input type="textarea" v-model="form.schema_json" :rows="10" placeholder='{"fields":[{"id":"name","type":"text","label":"姓名"}]}' />
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="dialogVisible=false">取消</el-button>
			<el-button type="primary" @click="save">保存</el-button>
		</template>
	</el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { http } from '@/shared/api'

const list = ref<any[]>([])
const dialogVisible = ref(false)
const editing = ref(false)
const currentId = ref<number | null>(null)
const form = reactive({ name: '', description: '', schema_json: '{"fields":[]}', is_published: false })

async function fetchList() {
	const res = await http.get('/templates')
	list.value = res.data
}

function openCreate() {
	editing.value = false
	Object.assign(form, { name: '', description: '', schema_json: '{"fields":[]}', is_published: false })
	dialogVisible.value = true
}

function openEdit(row: any) {
	editing.value = true
	currentId.value = row.id
	Object.assign(form, row)
	dialogVisible.value = true
}

async function save() {
	if (editing.value && currentId.value) {
		await http.put(`/templates/${currentId.value}`, form)
	} else {
		await http.post('/templates', form)
	}
	dialogVisible.value = false
	await fetchList()
}

async function togglePublish(row: any) {
	await http.put(`/templates/${row.id}`, { is_published: !row.is_published })
	await fetchList()
}

async function remove(row: any) {
	await http.delete(`/templates/${row.id}`)
	await fetchList()
}

onMounted(fetchList)
</script>
