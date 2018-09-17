package com.zuehlke.movieticketservice.api;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;

/**
 * Provides functionality to create rest clients using Feign
 */
public class RestClientFactory {

    public static <T> T createClient(String url, Class<T> clazz) {
        return createDefaultClient().target(clazz, url);
    }

    private static HystrixFeign.Builder createDefaultClient() {
        ObjectMapper mapper = new ObjectMapper()
                // ignores unknown properties in the json response
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        return HystrixFeign.builder()
                .decoder(new JacksonDecoder(mapper))
                .logger(new Slf4jLogger(RestClientFactory.class))
                .logLevel(Logger.Level.FULL);
    }
}
