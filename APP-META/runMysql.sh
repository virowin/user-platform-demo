#!/bin/zsh
docker build -f MySqlDockerfile -t mysql:5.7 .
docker run -eMYSQL_ROOT_PASSWORD=root --name mysql-demo -p3309:3306 mysql:5.7