#!/bin/bash
set +e



docker build -t backend-server:1.0 ./backend-server
docker build -t rest-server:1.0 ./rest-server

