<script setup lang="ts">
import { computed, ref, watch, toRefs } from 'vue'

interface FieldItem {
	id: string
	type: string
	label: string
	options?: Array<{ label: string; value: string }>
	placeholder?: string
	required?: boolean
}

const props = defineProps<{ schema: string | Record<string, any>; modelValue: Record<string, any> }>()
const emit = defineEmits(['update:modelValue'])

const inner = ref<Record<string, any>>({})

const fields = computed<FieldItem[]>(() => {
	let parsed: any = props.schema
	if (typeof props.schema === 'string') {
		try { parsed = JSON.parse(props.schema as string) } catch { parsed = { fields: [] } }
	}
	return parsed?.fields || []
})

watch(() => props.modelValue, (v) => { inner.value = { ...(v || {}) } }, { immediate: true, deep: true })
watch(inner, (v) => emit('update:modelValue', v), { deep: true })

function onChooseImage(fieldId: string) {
	uni.chooseImage({ count: 1, success: (res) => { inner.value[fieldId] = res.tempFilePaths?.[0] } })
}
function onChooseVideo(fieldId: string) {
	uni.chooseVideo({ success: (res) => { inner.value[fieldId] = (res as any).tempFilePath } })
}
function onChooseFile(fieldId: string) {
	uni.chooseMessageFile({ count: 1, success: (res) => { inner.value[fieldId] = res.tempFiles?.[0]?.path } })
}
</script>

<template>
	<view class="form">
		<view v-for="f in fields" :key="f.id" class="field">
			<view class="label">{{ f.label }}<text v-if="f.required" style="color:red">*</text></view>
			<input v-if="f.type==='text'" class="input" :placeholder="f.placeholder||'请输入'" v-model="inner[f.id]" />
			<radio-group v-else-if="f.type==='radio'" @change="(e:any)=> inner[f.id]=e.detail.value">
				<label v-for="opt in f.options||[]" :key="opt.value" class="row">
					<radio :value="opt.value" :checked="inner[f.id]===opt.value" /> {{ opt.label }}
				</label>
			</radio-group>
			<checkbox-group v-else-if="f.type==='checkbox'" @change="(e:any)=> inner[f.id]=e.detail.value">
				<label v-for="opt in f.options||[]" :key="opt.value" class="row">
					<checkbox :value="opt.value" :checked="(inner[f.id]||[]).includes(opt.value)" /> {{ opt.label }}
				</label>
			</checkbox-group>
			<picker v-else-if="f.type==='date'" mode="date" @change="(e:any)=> inner[f.id]=e.detail.value">
				<view class="picker">{{ inner[f.id] || '选择日期' }}</view>
			</picker>
			<picker v-else-if="f.type==='time'" mode="time" @change="(e:any)=> inner[f.id]=e.detail.value">
				<view class="picker">{{ inner[f.id] || '选择时间' }}</view>
			</picker>
			<view v-else-if="f.type==='image'">
				<button size="mini" @click="onChooseImage(f.id)">选择图片</button>
				<image v-if="inner[f.id]" :src="inner[f.id]" mode="aspectFit" style="width:200rpx;height:200rpx;" />
			</view>
			<view v-else-if="f.type==='video'">
				<button size="mini" @click="onChooseVideo(f.id)">选择视频</button>
				<text v-if="inner[f.id]">已选择</text>
			</view>
			<view v-else-if="f.type==='file'">
				<button size="mini" @click="onChooseFile(f.id)">选择文件</button>
				<text v-if="inner[f.id]">已选择</text>
			</view>
		</view>
	</view>
</template>

<style scoped>
.form { background: #fff; padding: 24rpx; border-radius: 12rpx; }
.field { margin-bottom: 20rpx; }
.label { font-size: 28rpx; margin-bottom: 8rpx; }
.input { background: #f7f7f7; padding: 16rpx; border-radius: 8rpx; }
.row { display: flex; align-items: center; gap: 8rpx; margin-right: 16rpx; }
.picker { background: #f7f7f7; padding: 16rpx; border-radius: 8rpx; }
</style>
