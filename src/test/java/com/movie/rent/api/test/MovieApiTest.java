package com.movie.rent.api.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.model.Movie;

import static org.junit.Assert.*;
public class MovieApiTest extends MovieRentalApplicationTests{

	@Autowired
	RestTemplate restTemplate;
	
	String sp = "http://localhost:8080"; 
	
	@Test
	public void testShouldReturnAllMovies(){
		List<Movie> list = restTemplate.getForObject(sp.concat("/getMovies"), List.class);
		assertEquals(list.size(),5);
	}
	
	@Test
	public void testShouldReturnAllDrama(){
		List<Movie> list = restTemplate.getForObject(sp.concat("/getMovies?genres=drama"), List.class);
		assertEquals(list.size(),2);
	}
	
	@Test
	public void testShouldReturnMovieDetails(){
		Movie movie = restTemplate.getForObject(sp.concat("/getMovie/name1"), Movie.class);
	}
}
