# City Route, A Microservices Example Proyect

## Components
- Config Server --> TODO: Git
- Eureka Server
- Zuul
- Ribbon

## Libraries
- Spring Boot
- Eureka
- Zuul
- Ribbon
- Spring Cloud Config
- Lombok
- Swagger

## How to run this example :
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
	