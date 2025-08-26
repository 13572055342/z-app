<template>
	<view class="container">
		<view class="title">提交成功</view>
		<button @click="goHome">返回首页</button>
	</view>
</template>

<script setup lang="ts">
function goHome() {
	uni.switchTab({ url: '/src/pages/index/index' })
}
</script>

<style scoped>
.container { padding: 24rpx; }
.title { font-size: 34rpx; margin-bottom: 16rpx; }
</style>
