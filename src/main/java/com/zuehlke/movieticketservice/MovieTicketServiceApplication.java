package com.zuehlke.movieticketservice;

import com.zuehlke.movieticketservice.api.movieservice.MovieServiceAdapter;
import com.zuehlke.movieticketservice.api.ratingservice.RatingServiceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketServiceApplication.class, args);
	}

	@Bean
	public MovieServiceAdapter movieServiceAdapter(@Value("${endpoint.movie-service.url}") String url) {
		// url defined in the application.properties file
		return new MovieServiceAdapter(url);
	}

	@Bean
	public RatingServiceAdapter ratingServiceAdapter(@Value("${endpoint.rating-service.url}") String url) {
		return new RatingServiceAdapter(url);
	}
}
