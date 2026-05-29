# 1. # 用国内可正常拉取的 Tomcat + JDK8 镜像
FROM tomcat:9-jdk8-openjdk

# 2. 设置时区，避免时间问题
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 3. 复制 war 包到 Tomcat 的 webapps 目录，命名为 sqq-springboot-starter.war
# 这样访问时就不用加项目名了，直接访问根路径
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# 4. 暴露 Tomcat 默认端口 8080
EXPOSE 8080

# 5. 启动 Tomcat（镜像默认的启动命令，不用自己写）
CMD ["catalina.sh", "run"]