package com.zuehlke.movieticketservice.api.ratingservice;

import com.zuehlke.movieticketservice.api.RestClientFactory;
import com.zuehlke.movieticketservice.domain.Rating;

import java.util.List;
import java.util.stream.Collectors;


public class RatingServiceAdapter {

    private final RatingServiceApi ratingServiceApi;

    public RatingServiceAdapter(String url) {
        this.ratingServiceApi = RestClientFactory.createClient(url, RatingServiceApi.class);
    }

    public List<Rating> getRatings(int movieId) {
        return ratingServiceApi.getRatingsByMovieId(movieId).stream()
                .map(RatingResponse::toRating)
                .collect(Collectors.toList());
    }

}
