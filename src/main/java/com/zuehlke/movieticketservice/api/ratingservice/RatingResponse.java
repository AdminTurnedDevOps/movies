package com.zuehlke.movieticketservice.api.ratingservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zuehlke.movieticketservice.domain.Rating;

public class RatingResponse {

    private final String source;
    private final String value;

    @JsonCreator
    public RatingResponse(
            @JsonProperty("source") String source,
            @JsonProperty("value") String value) {
        this.source = source;
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public String getValue() {
        return value;
    }

    public Rating toRating() {
        return new Rating(getSource(), getValue());
    }
}
