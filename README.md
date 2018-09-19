[![Build Status](https://travis-ci.com/ynnckth/movie-ticket-service.svg?branch=master)](https://travis-ci.com/ynnckth/movie-ticket-service)


#### Swagger Endpoint Documentation
http://localhost:8080/swagger-ui.html

#### Health Indicators
Take a look at the following docs for the health indicators:  
https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-endpoints

#### Circuit Breaker
Check the Hystrix Monitoring Dashboard:  
http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fhystrix.stream

The configuration is done in the `application.properties` file.

Ideas:  
- add frontend and setup build (angularjs, ts, webpack, gradle)
- add DB for movie/rating persistence
- add authentication to use the app

# Development

## Client / Frontend

The Angular client code is located under `src/main/webapp`
and was created using the angular-cli (see https://cli.angular.io/).

**Serve/watch client (live reload server)**  
To start a local dev server for the client code run:  
> `ng serve --open`    
http://localhost:4200

**Build client**
> `ng build`   
Builds the client into the `src/main/webapp/dist/` directory
