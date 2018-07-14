# City Route, A Microservices Example Proyect
Example application of microservice architecture using Spring Boot, Spring Cloud Netflix technology stack on docker containers.

## Components
- Config Server: Centraliced configuration for all microservices. It is built using Spring Cloud Config.
- Discovery Server: Mantains the registry of microservices and it is used by api gateway. It is built using Eureka Server, from Spring Cloud Netflix.
- Api gateway: Exposes the api of the microservices, it uses discovery service to locate them. It is built using Zuul, from Spring Cloud Netflix.
- City Server: Microservice that exposes data related with cities. It is built using Spring Boot, Spring JPA and uses H2 in memory database for persistence.
- Route Server: Microservice that consumes City Server API. It has client side load balance of request to city server API. It is built using Riboon, from Spring Cloud Netflix besides Spring Boot.

## Use of libraries
- Spring Boot: Framework for easy creation of microservices.
- Spring Cloud Config: To allow centraliced configuration.
- Spring Cloud Netflix: Technology stack for microservices that does not depend on platform.
- Docker, Docker-compose: Container technology used to not depend on local environment.
- Lombok: To reduce redundant/boilerplate code.
- Swagger: Open Api specification.

## How to run the example :
Make sure you have DOCKER_HOST enviromental variable set with your docker host tcp://docker-host:docker-port, on windows my have to set a few more. 

```sh
set DOCKER_HOST=tcp://192.168.99.100:2376
set DOCKER_MACHINE_NAME=default
set DOCKER_TLS_VERIFY=1
set DOCKER_TOOLBOX_INSTALL_PATH=C:\Program Files\Docker Toolbox
set DOCKER_CERT_PATH=C:\Users\josej\.docker\machine\machines\default
```

Host value can be checked running the following command:

```sh
docker-machine ip
```



You can run this at once editing the provided script with your own values.

```sh
env.bat
```

```sh
## build docker images
mvn clean package

##should display the docker images
docker images

##build and start up all instances
docker-compose up --build

##starts a 2nd instance of city-server
docker-compose scale city=2
```


Once all the services are up, the following URLs will be available

Address | Description
--- | ---
http://<\<docker-host>\>:9091 | Eureka service.
http://<\<docker-host>\>:9090/routes | Zuul route definitions
http://<\<docker-host>\>:9090/api/city-service/cities | City service through Zuul api gateway, looked up from Eureka registry
http://<\<docker-host>\>:9090/api/city-service/swagger-ui.html | City service Swagger-ui Tool
http://<\<docker-host>\>:9090/api/city-service/v2/api-docs | City service Open API specification
	