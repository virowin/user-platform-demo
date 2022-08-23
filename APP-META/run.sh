#!/bin/zsh
source ~/.bash_profile
cd ..
mvn clean package
cp user-platform-starter/target/user-platform-starter-1.0.jar APP-META/
cd APP-META
docker build -f MySqlDockerfile -t mysql:5.7 .
docker run -eMYSQL_ROOT_PASSWORD=root --name mysql-demo -p3309:3306 mysql:5.7 &

docker build --build-arg=../user-platform-starter/target/user-platform-starter-1.0.jar -t user-platform:1.0 .
docker run -p7333:7333 --name app-virowin-demo --link mysql-demo:mysql-link user-platform:1.0&
