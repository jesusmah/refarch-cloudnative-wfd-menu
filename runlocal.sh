echo "Running menu service on port 8180"
docker run --name menu -p 8180:8180 -p 9180:9180 --env-file ~/ibm-cloud-architecture/refarch-cloudnative-container-utils/env/env-eureka-only-local -d wfd-menu:latest

