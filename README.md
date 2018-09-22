[![Build Status](https://travis-ci.com/ynnckth/movie-ticket-service.svg?branch=master)](https://travis-ci.com/ynnckth/movie-ticket-service)

# Development

### Client / Frontend

The Angular client code is located under `src/main/webapp`
and was created using the angular-cli (see https://cli.angular.io/).

**Serve/watch client (live reload server)**  
To start a local dev server for the client code run:  
> `ng serve --open`    
http://localhost:4200

**Build client**
> `ng build`   
Builds the client into the `src/main/webapp/dist/` directory

### Server / Backend

Build the application: 
> `./gradlew build`

#### Swagger Endpoint Documentation
> http://localhost:8080/swagger-ui.html

#### Health Indicators
Take a look at the following docs for the health indicators:  
https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-endpoints

#### Circuit Breaker
Check the Hystrix Monitoring Dashboard:  
> http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fhystrix.stream

The configuration is done in the `application.properties` file.



# CI/CD Pipeline
This project uses **Travis CI** as a continuous integration and deployment platform (https://travis-ci.com/).  
The configuration is done in `travis.yml`. The Github repository of this project is referenced in the Travis project.

The config defines **Heroku** as a deployment target and pushes the app to Heroku on a successful build.  
The Heroku config is defined in `Procfile`.

