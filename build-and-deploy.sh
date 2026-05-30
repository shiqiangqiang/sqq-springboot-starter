#!/bin/bash

# -------------------------- 配置区（根据你的项目修改） --------------------------
APP_NAME="sqq-app"
SERVER_IP="101.132.78.113"
SERVER_DIR="/root/localImages"
# ------------------------------------------------------------------------------

echo "============================================="
echo "       SpringBoot Docker 一键构建 & 上传脚本"
echo "============================================="

# 1. 清理 Maven 缓存，强制重新打包
echo "[1/4] 正在执行 mvn clean package..."
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
  echo "❌ Maven 打包失败，请检查代码"
  exit 1
fi

# 2. 强制 Docker 不使用缓存，构建镜像
echo "[2/4] 正在构建 Docker 镜像（不使用缓存）..."
docker build --no-cache -t $APP_NAME .
if [ $? -ne 0 ]; then
  echo "❌ Docker 镜像构建失败，请检查 Dockerfile"
  exit 1
fi

# 3. 把镜像打包成 tar 文件
echo "[3/4] 正在打包镜像为 tar 文件..."
docker save -o ../aliyunapp/$APP_NAME.tar $APP_NAME
if [ $? -ne 0 ]; then
  echo "❌ 镜像打包失败"
  exit 1
fi

# 4. 上传到阿里云服务器
echo "[4/4] 正在上传镜像到服务器 $SERVER_IP..."
scp ../aliyunapp/$APP_NAME.tar root@$SERVER_IP:$SERVER_DIR
if [ $? -ne 0 ]; then
  echo "❌ 文件上传失败，请检查服务器地址和密钥"
  exit 1
fi

echo "✅ 所有操作完成！"
echo "👉 接下来你可以在服务器上执行："
echo "   docker load -i $SERVER_DIR/$APP_NAME.tar"
echo "   docker restart sqq-app-starter"