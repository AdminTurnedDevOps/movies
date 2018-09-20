package com.zuehlke.movieticketservice.integration;

import com.zuehlke.movieticketservice.api.movieservice.MovieServiceAdapter;
import com.zuehlke.movieticketservice.domain.Movie;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

/**
 * Tests the integration with the real external movie service
 */
@Ignore
public class MovieServiceAdapterIntegrationTest {

    // TODO: read from application.properties
    private static final String MOVIE_SERVICE_URL = "https://movie-service.herokuapp.com/";

    static {
        System.setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", "5000");
    }

    @Test
    public void getMovies() {
        MovieServiceAdapter movieServiceAdapter = new MovieServiceAdapter(MOVIE_SERVICE_URL);

        List<Movie> movies = movieServiceAdapter.getMovies();

        assertThat(movies, hasSize(7));
        assertThat(movies, hasItem(new Movie(1, "Batman Begins", "https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg")));
    }

    @Test
    public void getMovieById() {
        MovieServiceAdapter movieServiceAdapter = new MovieServiceAdapter(MOVIE_SERVICE_URL);

        Optional<Movie> movie = movieServiceAdapter.getMovieById(1);

        assertTrue(movie.isPresent());
        assertThat(movie.get().getId(), equalTo(1));
        assertThat(movie.get().getTitle(), equalTo("Batman Begins"));
    }
}
