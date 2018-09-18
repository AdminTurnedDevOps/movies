package com.zuehlke.movieticketservice.api.movieservice;

import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * Provides movies:
 * https://movie-service.herokuapp.com/swagger-ui.html#/movie-controller
 */
public interface MovieServiceApi {

    @RequestLine("GET /api/v1/movies")
    List<MovieResponse> getMovies();

    @RequestLine("GET /api/v1/movies/{id}")
    MovieResponse getMovieById(@Param("id") int id);

    @RequestLine("GET /health")
    void getHealthStatus();
}