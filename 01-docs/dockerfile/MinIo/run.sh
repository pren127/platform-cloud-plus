#!/bin/bash
cur_dir=`pwd`

docker stop platform_minio
docker rm platform_minio
docker run -p 9000:9000 --name platform_minio --restart=always \
  -d minio/minio server /data
#  -v /mnt/data:/data \
#  -v ${cur_dir}/config:/root/.minio \
