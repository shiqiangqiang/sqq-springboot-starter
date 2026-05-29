# 改用国内稳定的 OpenJDK 镜像
FROM openjdk:8-jre-alpine

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 工作目录
WORKDIR /app

# 复制打包好的 jar 包（名字改成你 target 目录下的 jar 全名）
COPY target/sqq-springboot-starter-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]