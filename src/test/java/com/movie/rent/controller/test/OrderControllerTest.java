package com.movie.rent.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.controller.OrderController;
import com.movie.rent.model.Movie;
import com.movie.rent.service.MovieService;

public class OrderControllerTest extends MovieRentalApplicationTests{
	
	@Autowired
	OrderController orderController;
	
	MockMvc mvc;
	
	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	MovieService movieServ;
	
	@Before
	public void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	public void testShouldReturnCart(){
		
	}
	
	@Test
	public void testShouldAddMovieToCart() throws Exception{
		List<Movie> movies = movieServ.getAllMovies();
		Movie movie1 = movies.get(0);
		ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/addToCart").
				param("id", movie1.getId()).
				param("name", movie1.getName()).
				param("actor", movie1.getActor()).
				param("actress", movie1.getActress()).
				param("releaseDate", movie1.getReleaseDate().toString()).
				param("genres", movie1.getGenres()).
				param("price", String.valueOf(movie1.getPrice())));
		movies.remove(movie1);
		result.andExpect(model().attribute("movieList", movies)).andExpect(view().name("viewAll"));
	}
	
	

}
