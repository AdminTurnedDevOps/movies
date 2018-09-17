package com.zuehlke.movieticketservice.integration;

import com.zuehlke.movieticketservice.api.ratingservice.RatingServiceAdapter;
import com.zuehlke.movieticketservice.domain.Rating;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Tests the integration with the real external movie-service
 */
public class RatingServiceAdapterIntegrationTest {

    // TODO: read from application.properties
    private static final String RATING_SERVICE_URL = "https://movie-rating-service.herokuapp.com/";

    static {
        System.setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", "5000");
    }

    @Test
    public void getRatings() {
        RatingServiceAdapter ratingServiceAdapter = new RatingServiceAdapter(RATING_SERVICE_URL);

        List<Rating> ratings = ratingServiceAdapter.getRatings(1);

        assertThat(ratings, hasSize(3));
    }
}
