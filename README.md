# 微博热搜系统

本项目是一个简洁高效的微博热搜系统，基于 Redis SortedSet 实现关键词的实时热度排行，支持关键词上报、删除、修改和热搜榜查询，并集成了 Swagger UI 用于接口文档和调试。

## 技术栈

- Spring Boot
- Spring Web
- Spring Data Redis
- Redis（SortedSet 数据结构）
- Swagger（springdoc-openapi）

---

## 功能介绍

| 功能       | 描述                                        |
| ---------- | ------------------------------------------- |
| 上报关键词 | 将关键词热度 +1，如果不存在则新建           |
| 获取热搜榜 | 获取热度最高的前 N 个关键词，按分数降序排列 |
| 删除关键词 | 从热搜中移除指定关键词                      |
| 接口文档   | 集成 Swagger，支持在线调试和查看参数说明    |

---

##  启动项目

1. 启动 Redis 服务（默认端口 6379）

2. 克隆项目，导入 IDE

3. 添加 Maven 依赖（推荐使用 JDK 17+）

4. 修改 `application.properties`：

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379