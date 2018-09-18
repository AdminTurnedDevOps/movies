package com.zuehlke.movieticketservice.api.ratingservice;

import com.zuehlke.movieticketservice.api.RestClientFactory;
import com.zuehlke.movieticketservice.domain.Rating;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class RatingServiceAdapter implements HealthIndicator {

    private final RatingServiceApi ratingServiceApi;
    private final String url;

    public RatingServiceAdapter(String url) {
        this.url = url;

        // This fallback mock will be used if the real rating-service fails.
        // The fallback service returns an empty list instead of the ratings.
        RatingServiceApi ratingServiceApiFallback = new RatingServiceApi() {
            @Override
            public List<RatingResponse> getRatingsByMovieId(int id) {
                return Collections.emptyList();
            }
            @Override
            public void getHealthStatus() { }
        };

        this.ratingServiceApi = RestClientFactory
                .createClientWithFallback(url, RatingServiceApi.class, ratingServiceApiFallback);
    }

    public List<Rating> getRatings(int movieId) {
        return ratingServiceApi.getRatingsByMovieId(movieId).stream()
                .map(RatingResponse::toRating)
                .collect(Collectors.toList());
    }

    @Override
    public Health health() {
        try {
            ratingServiceApi.getHealthStatus();
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
