# 使用阿里云镜像站的 OpenJDK 8 镜像，国内拉取稳定
FROM registry.aliyuncs.com/library/openjdk:8-jre-alpine

# 设置时区，避免时间问题
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 工作目录
WORKDIR /app

# 复制打包好的 jar 包到容器内
# 注意：这里要改成你 target 目录下的 jar 包全名
COPY target/sqq-springboot-starter-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口（和你的项目配置一致）
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]