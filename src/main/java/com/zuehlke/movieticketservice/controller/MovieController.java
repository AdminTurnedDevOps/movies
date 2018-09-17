package com.zuehlke.movieticketservice.controller;

import com.zuehlke.movieticketservice.domain.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    // TODO: extract into repository/service
    private List<Movie> movies;

    public MovieController() {
        movies = new ArrayList<>();
        movies.add(new Movie(1, "Batman Begins", "https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3"));
        movies.add(new Movie(2, "Ted", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ1OTU0ODcxMV5BMl5BanBnXkFtZTcwOTMxNTUwOA"));
        movies.add(new Movie(3, "Ted", "https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg"));
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movies;
    }

    @GetMapping("/movies/{id}")
    public Movie getMovies(@PathVariable("id") int id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find movie for id " + id));
    }

}