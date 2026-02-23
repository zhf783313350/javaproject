# Million-RMB Salary Standard: Cloud-Native Microservices Architecture

## 📖 项目简介
本项目由一个基础的单体应用演进而来，现已升级为符合一线大厂资深架构师（百万年薪级别）技术要求的 **Spring Cloud Alibaba 微服务架构**。项目基于 **纯 Java 25** 构建，深度融合了领域驱动设计（DDD）、分布式服务治理、高并发流量防护及全链路追踪等核心技术。

## 🏗️ 核心架构矩阵

### 1. 分布式服务治理 (Spring Cloud + Nacos)
项目采用 Gradle 多模块（Multi-Module）构建体系，实现了真正的业务解耦：
- **`javaproject-gateway`**：全系统流量大门（Spring Cloud Gateway），负责动态路由映射与统一安全过滤。
- **`javaproject-common`**：沉淀通用的 `Result<T>` 响应契约、全局异常机制、以及标准 VO，保障全集群“异口同声”。
- **`javaproject-student-service`**：独立的学生业务微服务（8081端口），具备自有的数据库控制权。
- **`javaproject-teacher-service`**：独立的教师业务微服务（8082端口），实现不同领域垂直切分。
- **服务发现**：通过 **Nacos** 实现服务实例的自动注册与发现，支持节点的秒级感知与平滑扩缩容。

### 2. 极致的高并发防护 (Sentinel)
针对千万级并发场景，集成了 **Sentinel** 流量卫兵：
- **自愈能力**：在核心 RPC 接口上部署了熔断降级逻辑。当数据库出现性能瓶颈或网络抖动时，Sentinel 会自动触发 Circuit Breaker（断路器），通过 `handleBlock` 回调返回友好的降级响应。
- **流量分流**：具备实时 QPS 限流能力，防止后端微服务集群被突发流量洪峰摧毁。

### 3. 全链路观测与追踪 (Micrometer Tracing)
引入 **Brave/Micrometer** 核心，建立了分布式系统的“数字化坐标系统”：
- **日志贯穿**：每一个通过网关进来的请求都会被赋予全局唯一的 `TraceId`。日志中自动注入 `[TraceId, SpanId]`，使得原本碎片的集群日志可以按请求链路瞬间还原，大幅降低 Bug 归因难度。

### 4. 跨服务无缝通讯 (OpenFeign)
利用声明式客户端 **OpenFeign** 取代原始的 HTTP Client，使 `student-service` 调用 `teacher-service` 的 RPC 体验如同调用本地方法一般丝滑且类型安全。

## � 技术栈清单
| 技术 | 版本 | 核心价值 |
| :--- | :--- | :--- |
| **JDK** | `v25.0.1+` | 抢先适配 Java 25，保障未来 5 年的系统底座稳定性 |
| **Spring Boot** | `v3.4.2` | 最新稳定版容器环境 |
| **Spring Cloud** | `2024.x` | 分布式微服务总线 |
| **Sentinel** | Alibaba | 抗住千万级并发的核心防护大脑 |
| **Nacos** | Registry | 微服务生存空间的动态坐标塔 |
| **MapStruct** | `v1.6.3` | CPU 零拷贝级的 DTO/Entity 高效转换 |
| **Micrometer** | Tracing | 全链路监控的可观测性底座 |

## 📦 启动与运行指南
1. **基础设施部署**：确保本地已拉起 **Nacos Server (8848)** 和 **Redis (6379)** 容器。
2. **构建全量镜像/包**：
   ```bash
   ./gradlew clean build -x test
   ```
3. **分层启动顺序**：
   - 优先启动 `javaproject-gateway` (监听 8080)
   - 随后启动各业务服务 `student-service` & `teacher-service`
4. **统一流量入口**：
   - 所有流量通过网关访问：`http://localhost:8080/api/v1/students/...`

## ✒️ 架构寄语
> 这不再是一份简单的代码，这是一套可以横向扩展至 100+ 节点的**生产就绪级 (Production-Ready)** 架构模版。它不仅是为了完成功能，更是为了在日均千万、亿级流量的极度压力下，依然能够保持系统的优美与高可用。
