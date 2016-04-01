package com.movie.rent.controller.test;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.controller.LoginController;
import com.movie.rent.model.Movie;
import com.movie.rent.service.MovieService;


public class MovieControllerTest extends MovieRentalApplicationTests{

	@Autowired
	LoginController loginController;
	
	MockMvc mvc;
	
	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	MovieService movieService;

	
	@Before
	public void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testShouldGetAllMovies() throws Exception{
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/getAllMovies"));
		result.andExpect(model().attribute("movieList", movieService.getAllMovies())).
		andExpect(view().name("viewAll"));
	}
	
	@Test
	public void testShouldGetDetailsForAMovie() throws Exception{
		Movie movieForTest = movieService.getAllMovies().get(0);
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/getMovieDetails").param("movie", movieForTest.getName()));
		result.andExpect(status().is2xxSuccessful()).
		andExpect(model().attribute("movie", hasProperty("id", is(movieForTest.getId())))).
		andExpect(model().attribute("movie", hasProperty("actor", is(movieForTest.getActor())))).
		andExpect(model().attribute("movie", hasProperty("releaseDate", is(movieForTest.getReleaseDate())))).
		andExpect(view().name("movieDetails"));
	}
	
	@Test
	public void testShouldReturnAllDrama() throws Exception{
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/getMoviesByGenres").param("genres", "drama"));
		result.andExpect(model().attribute("movieList", movieService.getMovieByGenres("drama"))).
		andExpect(view().name("viewAll"));;
	}

}
