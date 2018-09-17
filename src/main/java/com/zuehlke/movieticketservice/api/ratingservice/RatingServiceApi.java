package com.zuehlke.movieticketservice.api.ratingservice;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface RatingServiceApi {

    @RequestLine("GET /api/v1/ratings/{id}")
    List<RatingResponse> getRatingsByMovieId(@Param("id") int id);

}