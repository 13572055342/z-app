<template>
	<el-container style="height: 100vh">
		<el-aside width="220px">
			<div class="logo">z-admin</div>
			<el-menu :default-active="$route.path" router>
				<el-menu-item index="/">仪表盘</el-menu-item>
				<el-menu-item index="/templates">模板管理</el-menu-item>
				<el-menu-item index="/submissions">提交数据</el-menu-item>
				<el-menu-item index="/qrcodes">二维码管理</el-menu-item>
			</el-menu>
		</el-aside>
		<el-container>
			<el-header>自定义表单系统后台</el-header>
			<el-main>
				<router-view />
			</el-main>
		</el-container>
	</el-container>
</template>

<script setup lang="ts">
</script>

<style scoped>
.logo { height: 60px; display: flex; align-items: center; justify-content: center; font-weight: 700; }
</style>
