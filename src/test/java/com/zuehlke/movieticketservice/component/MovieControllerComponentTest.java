package com.zuehlke.movieticketservice.component;

import com.zuehlke.movieticketservice.api.movieservice.MovieServiceAdapter;
import com.zuehlke.movieticketservice.api.ratingservice.RatingServiceAdapter;
import com.zuehlke.movieticketservice.domain.Movie;
import com.zuehlke.movieticketservice.domain.Rating;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Matchers.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerComponentTest {

    @LocalServerPort
    private int port;

    @MockBean
    private MovieServiceAdapter movieServiceAdapter;

    @MockBean
    private RatingServiceAdapter ratingServiceAdapter;

    @Before
    public void setUp() {
        RestAssured.port = port;

        Mockito.when(movieServiceAdapter.getMovies()).thenReturn(getTestMovies());
        Mockito.when(movieServiceAdapter.getMovieById(anyInt())).thenReturn(Optional.of(getTestMovies().get(0)));

        Mockito.when(ratingServiceAdapter.getRatings(anyInt())).thenReturn(getTestRatings());
    }

    @Test
    public void getMovies() {
        when()
                .get("/api/v1/movies")
                .then()
                .statusCode(200)
                .body("get(0).id", equalTo(1))
                .body("get(0).title", equalTo("Batman Begins"))
                .body("get(1).id", equalTo(2))
                .body("get(1).title", equalTo("Ted"));
    }

    @Test
    public void getMovieById() {
        when()
                .get("/api/v1/movies/{id}", 1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Batman Begins"))
                .body("ratings.source", hasItems("Rotten Tomatoes"));
    }

    private List<Movie> getTestMovies() {
        Movie m1 = new Movie(1, "Batman Begins", "http://...");
        Movie m2 = new Movie(2, "Ted", "http://...");
        Movie m3 = new Movie(3, "Inception", "http://...");
        m1.setRatings(getTestRatings());
        return Arrays.asList(m1, m2, m3);
    }

    private List<Rating> getTestRatings() {
        return Collections.singletonList(new Rating("Rotten Tomatoes", "8.1"));
    }
}
