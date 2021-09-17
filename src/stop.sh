#!/bin/bash
set +e

docker stop rest-server
docker stop backend-server

docker network rm twitter-net