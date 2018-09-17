package com.zuehlke.movieticketservice.api.movieservice;

import com.zuehlke.movieticketservice.api.RestClientFactory;
import com.zuehlke.movieticketservice.domain.Movie;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class MovieServiceAdapter {

    private final MovieServiceApi movieServiceApi;

    public MovieServiceAdapter(String url) {
        movieServiceApi = RestClientFactory.createClient(url, MovieServiceApi.class);
    }

    public List<Movie> getMovies() {
        return movieServiceApi.getMovies().stream()
                .map(MovieResponse::toMovie)
                .collect(Collectors.toList());
    }

    public Optional<Movie> getMovieById(int id) {
        MovieResponse movieResponse = movieServiceApi.getMovieById(id);
        return Optional.of(movieResponse.toMovie());
    }
}
