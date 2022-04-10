package com.edgar.catalog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.edgar.catalog.model.CatalogItem;
import com.edgar.catalog.model.Movie;
import com.edgar.catalog.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalogByUserId(@PathVariable("userId") String userId) {
		System.out.println("Catalog controller called");
		
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/rating/users/" + userId, UserRating.class);
		
		return ratings.getUseRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "desc", rating.getRating());
		}).collect(Collectors.toList());	
	}
}

/*
 * 
 * Movie movie = webClientBuilder.build()
					.get()
					.uri("http://localhost:8082/movies/" + rating.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();
 * 
 */