# Million-RMB Salary Standard Java Architecture

## 📖 项目简介
本项目旨在展示符合一线大厂资深架构师（百万年薪级别）技术要求的现代化企业级 Java 后端架构。项目已从基础的 CRUD 面条代码，彻底重构为具备高扩展性、高性能、高可维护性的 **纯 Java 25** 工程框架。

## 🚀 核心架构升级亮点

### 1. 领域驱动设计 (DDD) 严格分层
彻底告别混乱的包结构，采用职责清晰的水平分层：
- `api`：**接口层**。专注于 HTTP 请求的接入、参数校验、和 Swagger API 文档整合，数据回写。
- `application`：**应用层**。专注于业务用例的编排，结合 MapStruct 处理 Entity 和 VO 之间的高性能映射。
- `domain`：**领域层**。系统的绝对核心（实体与模型），隔离一切外部框架污染。
- `infrastructure`：**基础设施层**。提供公共组件、数据库连接配置、拦截器、缓存策略等底层支持。

### 2. Pure Java 25 适配
全面拥抱官方最新技术特性，为了兼容 **Java 25 (Early Access)** 编译器底层变动，**主动移除了 Lombok 插件**，采用纯净、严谨的标准 Java 对象结构（Getter/Setter/Constructor），保障系统在未来环境中的绝对稳定性。

### 3. 企业级 API 桥梁
- **统一结果规范 (`Result<T>`)**：任何 API 的出参都经过严格包装，包含 `code`, `message`, `data`, `timestamp`，为前端提供高度一致的解析契约。
- **全局异常拦截 (`GlobalExceptionHandler`)**：采用 AOP 思想，告别低级 `try-catch`。自定义 `BusinessException`，将业务异常与系统级别异常隔离，返回友好的 JSON 提示，而不是抛出堆栈。

### 4. DTO/VO 安全防腐层
强校验内外数据。将数据库实体类 (`Entity`) 绝对封闭在系统内部。对外部 API 暴露定制化的 `VO` (View Object)，保护了如密码、内部状态等隐私数据不被外泄。

### 5. 依赖注入最佳实践
彻底摒弃导致循环依赖隐患的 `@Autowired` 属性注入模式，全线改用 **Spring 官方推荐的构造器注入模式 (Constructor Injection)**，让类依赖项在初始化时不可变，大幅降低空指针异常。

## 🛠️ 技术栈清单
| 组件 | 版本 | 备注 |
| :--- | :--- | :--- |
| **JDK** | `v25.0.1+8-27` | 最新官方稳定发行版 API |
| **Spring Boot** | `v3.4.2` | 当前最新长期维护版本 |
| **MapStruct** | `v1.6.3` | 高级预编译对象映射框架，性能远超反射 |
| **Spring Data JPA** | 整合 | 提供底层数据持久化 |
| **Spring Doc / Swagger3** | `v2.8.4` | 自动生成 API 交互文档 |
| **Redis** | 集成准备 | 配置完毕，可支持横向扩展高并发缓存 |

## 📦 如何启动本项目
1. **环境准备**：确认本地已安装并配置好 **Java 25** 和相应的环境变量。
2. **刷新依赖**：使用 `gradlew` 工具同步并刷新后端依赖包：
   ```bash
   ./gradlew clean build -x test
   ```
3. **运行启动类**：运行 `src/main/java/com/example/demo/DemoApplication.java`。
4. **接口预览调试**：项目启动成功后，访问：
   ```text
   http://localhost:8080/swagger-ui/index.html
   ```

## ✒️ 架构寄语
> 这套基于 Java 25 的微服务单体底座，融合了当前互联网一线业务开发最苛刻的规范与准则。它不再是一个代码堆砌的 Demo，而是可以立刻承载大型业务开发的工程化脚手架。
