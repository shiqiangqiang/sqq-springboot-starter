# 用中科大 / 清华源，直接能拉，不用阿里云
FROM docker.mirrors.ustc.edu.cn/library/openjdk:8-jre-alpine
# 或者
# FROM hub-mirror.c.163.com/library/openjdk:8-jre-alpine

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]