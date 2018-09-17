package com.zuehlke.movieticketservice.controller;

import com.zuehlke.movieticketservice.api.movieservice.MovieServiceAdapter;
import com.zuehlke.movieticketservice.domain.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    private final MovieServiceAdapter movieServiceAdapter;

    public MovieController(MovieServiceAdapter movieServiceAdapter) {
        this.movieServiceAdapter = movieServiceAdapter;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieServiceAdapter.getMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovies(@PathVariable("id") int id) {
        return movieServiceAdapter.getMovies().stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find movie for id " + id));
    }

}