package com.edgar.info.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.info.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		System.out.println("Movie controller called");
		return new Movie(movieId, "movie name");
	}
}
