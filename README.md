# 研究分布式环境下如何协调有限资源的分配

## 问题描述

现在有 M 个资源点, 有 N 台服务器, N 台服务器需要能协调完成对 M 个资源点的分配

**描述:** 

1. 每个资源点最多被 1 台服务器占用
2. 1 台服务器可以同时使用多个资源点
3. 需要提供多种分配策略, 帮助使用者能在不同场景下使用

### 简化问题

1. M 个资源点 与 N 台服务器, 数量都固定, 如何完成资源分配
2. M 个资源点数量固定, 但服务器会有增减, 如何完成资源分配
3. 资源点和服务器都不固定, 如何完成资源分配

## 测试

### 启动

```
# 需要 maven 环境, 本项目使用 jdk11
mvn clean package -DskipTests
java -jar target/distributed-limited-resource-allocation-{version}.jar --spring.profiles.active=s1
java -jar target/distributed-limited-resource-allocation-{version}.jar --spring.profiles.active=s2
java -jar target/distributed-limited-resource-allocation-{version}.jar --spring.profiles.active=s3
```

### swagger

- http://localhost:7001/swagger-ui.html
- http://localhost:7002/swagger-ui.html
- http://localhost:7003/swagger-ui.html










