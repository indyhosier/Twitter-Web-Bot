#!/bin/bash

docker run --rm --name rest-server -d -p 8888:8888 rest-server:1.0
docker logs -f rest-server
