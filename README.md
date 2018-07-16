# City Route, A Microservices Example Proyect
Example application of microservice architecture using Spring Boot, Spring Cloud Netflix technology stack on docker containers.

## Components
- Config Server: Centraliced configuration for all microservices. It is built using Spring Cloud Config.
- Discovery Server: Mantains the registry of microservices and it is used by api gateway. It is built using Eureka Server, from Spring Cloud Netflix.
- Api gateway: Exposes the api of the microservices, it uses discovery service to locate them. It is built using Zuul, from Spring Cloud Netflix.
- City Server: Microservice that exposes data related with cities. It is built using Spring Boot, Spring JPA and uses H2 in memory database for persistence.
- Route Server: Microservice that consumes City Server API. It has client side load balance of request to city server API. It is built using Riboon, from Spring Cloud Netflix besides Spring Boot.

![Architecture diagram](https://image.ibb.co/e1SYgy/Diagram.jpg "Architecture diagram")

Swagger API definition is available in repository 
- /calculation-server/swagger-api.json
- /city-server/swagger-api.json

## Use of libraries
- Spring Boot: Framework for easy creation of microservices.
- Spring Cloud Config: To allow centraliced configuration.
- Spring Cloud Netflix: Technology stack for microservices that does not depend on platform / container features.
	- Feign: Declarative rest client. Used to make calls between microservices using Eureka, Riboon and Hystrix.
	- Riboon: Client load balance between instances of same service.
	- Hystrix: Circuit breaker used to use an alternative response when required service is down.
	- Zuul: Api gateway that uses Eureka and Riboon.
	- Eureka: To register and discover services, used by the rest of the components to locate services.
- Docker, Docker-compose: Container technology used to not depend on local environment.
- Lombok: To reduce redundant/boilerplate code.
- Swagger: Open Api specification.
- JPA: ORM to manage database with an object oriented approach.
- Mockito: Test framework for mock components.

I have used Feign to make calls between calculation MS and cities MS. It internally uses hystrix, riboon and eureka, however, hystrix fallback method not working, most likely due to some bug on hystrix or riboon. I didn't have time to further investigate this. Hystrix works well using another client like RestTemplate.

## How to run the example :
Make sure you have DOCKER_HOST enviromental variable set with your docker host tcp://docker-host:docker-port, on windows my have to set a few more.

```sh
set DOCKER_HOST=tcp://192.168.99.100:2376
set DOCKER_MACHINE_NAME=default
set DOCKER_TLS_VERIFY=1
set DOCKER_TOOLBOX_INSTALL_PATH=C:\Program Files\Docker Toolbox
set DOCKER_CERT_PATH=C:\Users\josej\.docker\machine\machines\default
```

Host value can be localhost, but if you're using boot2docker, you must access it via the boot2docker IP address instead of localhost. If you're using Docker Toolbox, you must access it via the docker-machine IP address instead of localhost.

```sh
docker-machine ip
```

You can run this at once editing the provided script with your own values.

```sh
## Sets neceary enviromental variables in my windows machine, change it if you need with your own values
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
http://<\<docker-host>\>:9090/api/city-server/cities | List all cities. City service through Zuul api gateway, looked up from Eureka registry
http://<\<docker-host>\>:9090/api/city-server/cities/{name} | Get requested City. City service through Zuul api gateway, looked up from Eureka registry
http://<\<docker-host>\>:9090/api/city-server/routes | List all routes. City service through Zuul api gateway, looked up from Eureka registry
http://<\<docker-host>\>:9090/api/city-server/routes/{origin} | List all routes with origin in requested City. City service through Zuul api gateway, looked up from Eureka registry
http://<\<docker-host>\>:9090/api/city-server/swagger-ui.html | City service Swagger-ui Tool
http://<\<docker-host>\>:9090/api/city-server/v2/api-docs | City service Open API specification
http://<\<docker-host>\>:9090/api/calculation-server/calculations/connections/{origin}/{destination} | Get less connections route from origin to destination. Calculations service through Zuul api gateway, looked up from Eureka registry
http://<\<docker-host>\>:9090/api/calculation-server/calculations/duration/{origin}/{destination} | Get less duration route from origin to destination. Calculations service through Zuul api gateway, looked up from Eureka registry
http://<\<docker-host>\>:9090/api/calculation-server/swagger-ui.html | Calculation service Swagger-ui Tool
http://<\<docker-host>\>:9090/api/calculation-server/v2/api-docs | Calculation service Open API specification

## Microservices definitions

### City Server

Microservice to provide information about cities and routes

Method | Path | Description
--- | --- | ---
GET | /cities | List all cities.
GET | /cities/{name} | Get requested City.
GET | /routes | List all routes.
GET | /routes/{origin} | List all routes with origin in requested City.

### Calculation Server

Microservice to provide calculations about best routes using City Server API

Method | Path | Description
--- | --- | ---
GET | /calculations/connections/{origin}/{destination} | Get less connections route from origin to destination.
GET | /calculations/duration/{origin}/{destination} | Get less duration route from origin to destination.

## Database design

Definition of the database table model used. There is sample data available for test.

### CITIES table

There are 8 cities available, loaded with the following script;

```sql
insert into cities(id, name) values(1,'Zaragoza');
insert into cities(id, name) values(2,'Madrid');
insert into cities(id, name) values(3,'Barcelona');
insert into cities(id, name) values(4,'Teruel');
insert into cities(id, name) values(5,'Valencia');
insert into cities(id, name) values(6,'La Coruña');
insert into cities(id, name) values(7,'Cáceres');
insert into cities(id, name) values(8,'Sevilla');
```

### ROUTES table

There are 4 routes available, 3 from Zaragoza to Sevilla to test route calculations and 1 from Madrid to Barcelona:

```sql
-- Zgz - Sev
insert into routes(id, origin_id, destination_id) values(1,1,8);
insert into routes(id, origin_id, destination_id) values(2,1,8);
insert into routes(id, origin_id, destination_id) values(3,1,8);
-- Mad - Bcn
insert into routes(id, origin_id, destination_id) values(4,2,3);
```

### STEPS table

Routes have steps, which are the connections between cities and have a duration in hours

```sql
-- Zgz - Sev (Route 1)
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(1,1,1,1,2,3);
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(2,1,2,2,3,3);
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(3,1,3,3,8,4);

-- Zgz - Sev (Route 2)
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(4,2,1,1,2,3);
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(5,2,2,2,8,6);

-- Zgz - Sev (Route 3)
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(6,3,1,1,8,11);

-- Mad - Bcn (Route 1)
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(7,4,1,2,1,3);
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(8,4,2,1,3,3);
```

## Improvement points

Because of lack of time and the fact that this is a proof of concept, there are things that could be improved, here is a few of them:

- Database should be autonomous and not embedded/in memory one. And ideally should be replicated in order to assure high availability.
- ELK (Elastic Search, Logstash, Kibana) system should be in place in order to monitor logs of distributed system.
- Cloud config is in place but it is not configured to use a GIT repository using ssh keys, that should be the production configuration.
- Many components are configured by default (Like Riboon load balance method, timeouts...), this should be properly configured based on network topology.

## Pipeline proposal for application

First of all, this application has five modules in one repository. This is a POC, in production it should have one repository for each module, in order to allow separate life cycles between modules (And probably development teams). 

This, and the fact that there are no libraries in common between components and each component can have is own version, is interesting to provide the ability of make frequent updates.

That being said, here is a simple pipeline proposal for each module of the application.

- Developer Commits to his branch and creats pull request to RELEASE branch (Git)
- Team lead does code review and approves pull request (Stash / Bitbucker / GitLab)
- CI passes unit tests (maven or gradle)
- CI passes code quality (SonarQube plugin)
- CI passes integration tests of dependant components (Smartbear soap ui plugin for maven)
- CI builds artifacts (jars and docker images) and publish to Nexus or Artifactory
- CI deploys docker images to staging (using proper tool acording to platform, like oc, kubectl...)
- CI passes acceptance tests
- CI deploys to production (Maybe with manual approval)
- CI passes smoke test to production.
