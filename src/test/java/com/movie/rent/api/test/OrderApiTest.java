package com.movie.rent.api.test;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.model.CartView;
import com.movie.rent.model.Movie;

import static org.junit.Assert.*;

public class OrderApiTest extends MovieRentalApplicationTests{

	@Autowired
	RestTemplate template;
	
	@Autowired
	HttpSession session;
	String sp = "http://localhost:8080"; 
	
	@Test
	public void testShouldAddMovieToCart(){
		Movie movie = new Movie("testMovie", new Date(), "testActor", "testActress", "testGenres", 1000);
		template.put(sp.concat("/api/addToCart"), movie);
	}
	
	@Test
	public void testShouldReturnMoviesInCart(){
		Movie movie1 = new Movie("testMovie1", new Date(), "testActor1", "testActress1", "testGenres1", 1000);
		Movie movie2 = new Movie("testMovie2", new Date(), "testActor2", "testActress2", "testGenres2", 1000);
		ResponseEntity<byte[]> response = template.exchange(sp.concat("/api/addToCart"), HttpMethod.PUT, new HttpEntity(movie1), byte[].class);		 
		String sessionId = response.getHeaders().get(HttpHeaders.SET_COOKIE).get(0).split(";")[0].trim();
		MultiValueMap<String, String> httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.COOKIE, sessionId);
		template.exchange(sp.concat("/api/addToCart"), HttpMethod.PUT, new HttpEntity(movie2, httpHeaders) , byte[].class);		
		ResponseEntity<CartView> cart = template.exchange(sp.concat("/api/showCart"), HttpMethod.GET, new HttpEntity<>(httpHeaders), CartView.class);
		assertEquals(cart.getBody().getMovies().size(),2);
		
	}
	
}
