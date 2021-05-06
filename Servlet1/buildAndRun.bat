@echo off
call mvn clean package
call docker build -t A/Servlet1 .
call docker rm -f Servlet1
call docker run -d -p 9080:9080 -p 9443:9443 --name Servlet1 A/Servlet1