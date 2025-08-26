# Miniapp (uni-app + Vue3)

运行
1. 安装依赖：在 `miniapp/` 执行 `npm i`
2. 配置后端地址：创建 `.env`（参考下面变量）
   - `VITE_API_BASE=http://localhost:8080/api`
3. 运行 H5：`npm run dev:h5`（调试，默认端口 5173/5174）
4. 运行微信小程序：`npm run dev:mp-weixin`，用微信开发者工具导入 `dist/build/mp-weixin`

结构
- `src/pages/index` 模板列表
- `src/pages/fill` 动态渲染并提交
- `src/pages/result` 提交结果
- `src/components/FormRenderer.vue` 表单渲染器

