package com.zuehlke.movieticketservice.controller;

import com.zuehlke.movieticketservice.api.movieservice.MovieServiceAdapter;
import com.zuehlke.movieticketservice.api.ratingservice.RatingServiceAdapter;
import com.zuehlke.movieticketservice.domain.Movie;
import com.zuehlke.movieticketservice.domain.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    private final MovieServiceAdapter movieServiceAdapter;
    private final RatingServiceAdapter ratingServiceAdapter;

    public MovieController(MovieServiceAdapter movieServiceAdapter, RatingServiceAdapter ratingServiceAdapter) {
        this.movieServiceAdapter = movieServiceAdapter;
        this.ratingServiceAdapter = ratingServiceAdapter;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieServiceAdapter.getMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable("id") int id) {
        List<Rating> ratings = ratingServiceAdapter.getRatings(id);

        return movieServiceAdapter.getMovieById(id)
                .map(movie -> {
                    movie.setRatings(ratings);
                    return movie;
                })
                .orElseThrow(() -> new RuntimeException("Could not find movie for id " + id));
    }

}