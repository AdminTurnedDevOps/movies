[![CircleCI](https://circleci.com/gh/ynnckth/movies.svg?style=svg)](https://circleci.com/gh/ynnckth/movies)

# Movies
Fullstack example project using Spring Boot, Angular and other state-of-the-art components and tools.  
This is the demo app used in the course [*Building Microservices with Spring Boot*](https://github.com/ynnckth/building-microservices).


#### Links
- [CI/CD on CircleCI](https://circleci.com/gh/ynnckth/movies)
- [Running app on Heroku](https://my-awesome-movies.herokuapp.com/)
- [Backend server Swagger API documentation](http://localhost:8080/swagger-ui.html)
- [Hystrix Monitoring Dashboard (Circuit Breaker)](http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fhystrix.stream)


#### Run locally
First you need to build the app. This preconfigured script creates a Docker image containing the app.
> `sh ./scripts/build.sh`

Start the containerized app using Docker (starts app running on port 8080):
> `sh ./scripts/run.sh`

Alternatively you can build the application without using Docker:
> `./gradlew build`

Run the built jar (starts the app running on port 8080):
> `java -jar build/libs/<artifact>.jar`


#### Development
Start the the backend server by running the `MovieTicketServiceApplication.java` or running:
> `./gradlew bootRun`  
will start the server running on port 8080


Start a frontend development server in watch (live reload) mode for the Angular app:
Navigate to the webapp dir located at `src/main/webapp/` and run:
> `ng serve`  
navigate to http://localhost:4200
