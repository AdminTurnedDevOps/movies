package com.zuehlke.movieticketservice;

import com.zuehlke.movieticketservice.api.movieservice.MovieServiceAdapter;
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
	public MovieServiceAdapter movieServiceAdapter() {
		return new MovieServiceAdapter("https://movie-service.herokuapp.com/");
	}
}
