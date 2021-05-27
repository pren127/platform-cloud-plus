#!/bin/bash
cur_dir=`pwd`

docker stop platform_mysql
docker rm platform_mysql
docker run --name platform_mysql --restart=always \
    -v `pwd`/conf:/etc/mysql/conf.d \
    -v /data/docker-data/mysql-data/:/var/lib/mysql \
    -p 3218:3306 \
    -e MYSQL_ROOT_PASSWORD="root" \
    -e TZ=Asia/Shanghai \
    -d mysql:5.7.9
