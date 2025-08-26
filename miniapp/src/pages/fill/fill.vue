<script setup lang="ts">
import { onMounted, ref } from 'vue'
import FormRenderer from '../../components/FormRenderer.vue'

const apiBase = import.meta.env.VITE_API_BASE || 'http://localhost:8000/api'
const template = ref<any | null>(null)
const submitting = ref(false)
const formData = ref<Record<string, any>>({})

function getQuery(key: string) {
	const q = uni.getCurrentPages().slice(-1)[0]?.options as any
	return q?.[key]
}

async function loadTemplate() {
	const id = getQuery('templateId')
	if (!id) return
	const res = await uni.request({ url: `${apiBase}/templates/${id}`, method: 'GET' })
	template.value = res.data
}

async function onSubmit() {
	if (!template.value) return
	submitting.value = true
	try {
		await uni.request({
			url: `${apiBase}/submissions`,
			method: 'POST',
			data: {
				template_id: template.value.id,
				submitter_openid: '',
				data_json: JSON.stringify(formData.value),
			},
		})
		uni.redirectTo({ url: `/pages/result/result` })
	} finally {
		submitting.value = false
	}
}

onMounted(loadTemplate)
</script>

<template>
	<view class="container" v-if="template">
		<view class="title">{{ template.name }}</view>
		<FormRenderer :schema="template.schema_json" v-model="formData" />
		<button class="submit" :loading="submitting" @click="onSubmit">提交</button>
	</view>
	<view v-else class="container">加载中...</view>
</template>

<style scoped>
.container { padding: 24rpx; }
.title { font-size: 34rpx; margin-bottom: 16rpx; }
.submit { margin-top: 24rpx; background: #2d8cf0; color: #fff; }
</style>
