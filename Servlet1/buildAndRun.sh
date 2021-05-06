#!/bin/sh
mvn clean package && docker build -t A/Servlet1 .
docker rm -f Servlet1 || true && docker run -d -p 9080:9080 -p 9443:9443 --name Servlet1 A/Servlet1