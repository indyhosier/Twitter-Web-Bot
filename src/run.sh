#!/bin/bash
set +e


docker network create twitter-net

docker run --rm --network=twitter-net --name backend-server -d backend-server:1.0
docker run --rm --network=twitter-net --name rest-server -d -p 8888:8888 rest-server:1.0

docker logs -f rest-server
