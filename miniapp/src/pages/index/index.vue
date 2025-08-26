<script setup lang="ts">
import { onMounted, ref } from 'vue'

const apiBase = import.meta.env.VITE_API_BASE || 'http://localhost:8000/api'
const loading = ref(false)
const templates = ref<Array<any>>([])

async function fetchTemplates() {
	loading.value = true
	try {
		const res = await uni.request({ url: `${apiBase}/templates`, method: 'GET' })
		templates.value = (res.data as any) || []
	} finally {
		loading.value = false
	}
}

function goFill(template: any) {
	uni.navigateTo({ url: `/pages/fill/fill?templateId=${template.id}` })
}

onMounted(fetchTemplates)
</script>

<template>
	<view class="container">
		<view class="title">表单模板</view>
		<view v-if="loading">加载中...</view>
		<view v-else>
			<view v-for="t in templates" :key="t.id" class="card" @click="goFill(t)">
				<view class="name">{{ t.name }}</view>
				<view class="desc">{{ t.description }}</view>
			</view>
		</view>
	</view>
</template>

<style scoped>
.container { padding: 16rpx; }
.title { font-size: 34rpx; margin: 16rpx 0; }
.card { background: #fff; padding: 24rpx; border-radius: 12rpx; margin-bottom: 16rpx; }
.name { font-weight: 600; font-size: 30rpx; margin-bottom: 8rpx; }
.desc { color: #666; font-size: 26rpx; }
</style>
