package com.edgar.rating.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.rating.model.Rating;
import com.edgar.rating.model.UserRating;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@GetMapping("/{movieId}")
	public Rating getRatingInfo(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 3);
	}
	
	@GetMapping("users/{userId}")
	public UserRating getRatingsByUser(@PathVariable("userId") String userId) {
		System.out.println("Rating controller called");
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 7),
				new Rating("4567", 8)
		);
		return new UserRating(ratings);
	}
}
