#!/bin/bash

mvn clean package

cp target/wfd-menu-0.0.1-SNAPSHOT.jar docker/app.jar

cd docker/

docker build -t wfd-menu .
