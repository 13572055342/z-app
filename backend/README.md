# Backend (Spring Boot 2 + JPA)

## 运行
1. 进入 `backend/`
2. 使用 Maven 构建并运行：
   - Windows：`mvnw.cmd spring-boot:run`（或 `mvn spring-boot:run`）
3. 默认端口：`8080`，API 前缀：`/api`

## 配置
- `src/main/resources/application.properties`
  - 本地默认 MySQL 示例（修改用户名/密码/库名）
  - 可改为 H2（示例已在注释中）
- JPA：`spring.jpa.hibernate.ddl-auto=update`（开发场景自动建表）

## 主要模块
- 实体：`domain/`（`AdminUser`, `FormTemplate`, `FormSubmission`, `QrCode`）
- 数据访问：`repository/`
- 控制器：`web/`（健康检查、模板、二维码、提交、统计）

## 常用命令
- 启动：`mvn spring-boot:run`
- 打包：`mvn clean package`

## 简单接口说明（示例）
- 健康检查：`GET /api/health`
- 模板：`GET/POST /api/templates`
- 提交：`POST /api/submissions`
- 二维码：`GET/POST /api/qrcodes`
- 统计：`GET /api/stats`
