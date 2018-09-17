package com.zuehlke.movieticketservice.component;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerComponentTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
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
                .body("title", equalTo("Batman Begins"));
    }
}
