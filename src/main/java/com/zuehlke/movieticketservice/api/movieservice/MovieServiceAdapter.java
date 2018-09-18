package com.zuehlke.movieticketservice.api.movieservice;

import com.zuehlke.movieticketservice.api.RestClientFactory;
import com.zuehlke.movieticketservice.domain.Movie;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class MovieServiceAdapter implements HealthIndicator {

    private final MovieServiceApi movieServiceApi;
    private final String url;

    public MovieServiceAdapter(String url) {
        this.url = url;
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

    /**
     * Registers the endpoint in the app's health indicator
     * The status is included in the /health endpoint of the app.
     */
    @Override
    public Health health() {
        try {
            movieServiceApi.getHealthStatus();
            return Health.up()
                    .withDetail("Endpoint", url)
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("Endpoint", url)
                    .build();
        }
    }
}
