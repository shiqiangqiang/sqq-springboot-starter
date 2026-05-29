# 1. 改用国内拉取稳定的 Tomcat 镜像（JDK8 + Tomcat9）
FROM tomcat:9.0-jdk8-corretto

# 2. 设置时区，避免时间问题
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 3. 复制 war 包到 Tomcat 的 webapps 目录，命名为 sqq-springboot-starter.war
# 这样访问时就不用加项目名了，直接访问根路径
COPY target/sqq-springboot-starter-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# 4. 暴露 Tomcat 默认端口 8080
EXPOSE 8080

# 5. 启动 Tomcat（镜像默认的启动命令，不用自己写）
CMD ["catalina.sh", "run"]