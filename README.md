[![Build Status](https://travis-ci.com/ynnckth/movie-ticket.svg?branch=master)](https://travis-ci.com/ynnckth/movie-ticket)


#### Swagger Endpoint Documentation
http://localhost:8080/swagger-ui.html

#### Health Indicators
Take a look at the following docs for the health indicators:  
https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-endpoints

#### Circuit Breaker
Check the Hystrix Monitoring Dashboard:  
http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fhystrix.stream

The configuration is done in the `application.properties` file.

TODO: 
- Add frontend (including build)
- Move external services (movie-service, rating-service) into this repository and manage using gradle multimodule project
- Describe Feign as RestClient (propose alternatives)
- Check how many instances are created (by request?, threads?)
- Check RestTemplate instead of Feign for RestClients