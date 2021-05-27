#!/bin/bash

mkdir -p /data/docker-data/rabbitmq-data/

docker stop platform_rabbitmq
docker rm platform_rabbitmq

docker run -d --hostname platform_rabbitmq --name platform_rabbitmq --restart=always \
    -e RABBITMQ_DEFAULT_USER=platform -e RABBITMQ_DEFAULT_PASS=platform \
    -v /data/docker-data/rabbitmq-data/:/var/rabbitmq/lib \
    -p 15672:15672 -p 5672:5672 -p 25672:25672 -p 61613:61613 -p 1883:1883 \
    -e TZ="Asia/Shanghai" \
    rabbitmq:management
