package com.movie.rent.controller.test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.controller.OrderController;
import com.movie.rent.model.Cart;
import com.movie.rent.model.Movie;
import com.movie.rent.model.Order;
import com.movie.rent.model.User;
import com.movie.rent.service.MovieService;

public class OrderControllerTest extends MovieRentalApplicationTests{
	
	@Autowired
	OrderController orderController;
	
	MockMvc mvc;
	
	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	MovieService movieServ;
	
	@Autowired
	Cart cart;
	
	@Autowired
	MongoTemplate template;
	
	@Before
	public void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	public void testShouldReturnCart() throws Exception{
		Movie movie = movieServ.getAllMovies().get(0);
		cart.addToCart(movie);
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/showCart"));
		result.andExpect(model().attribute("cart",hasProperty("movieList",contains(movie))));
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
				param("releasedate", movie1.getReleaseDate().toString()).
				param("genres", movie1.getGenres()).
				param("price", String.valueOf(movie1.getPrice())));
		movies.remove(movie1);
		result.andExpect(status().is2xxSuccessful()).andExpect(model().attribute("movieList", movies)).andExpect(view().name("viewAll"));
	}
	
	@Test
	public void testShouldSaveOrder() throws Exception{
		template.dropCollection(Order.class);
		cart.addToCart(movieServ.getAllMovies().get(1));
		User user = template.findOne(Query.query(Criteria.where("name").is("user1")), User.class);
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/placeOrder").sessionAttr("user", user));
		result.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/viewOrders"));
		Order order =template.findOne(Query.query(Criteria.where("userId").is(user.getId())), Order.class);
		
		assertTrue(order.getMovieList().containsAll(cart.getMovieList()));
		
	
	}
	/*
	public void testShouldGetOrderByUser() throws Exception{
		User user = template.findOne(Query.query(Criteria.where("name").is("user1")), User.class);
		Order order = new Order();
		order.setMovieList(movieServ.getAllMovies());
		order.setRentDate(new Date());
		order.setReturnDate(new Date());
		order.setOrderAmount();
		order.setUserId(user.getId());
		template.dropCollection(Order.class);
		template.save(order);
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/viewOrders").sessionAttr("user", user));
		result.andExpect(model().attribute("orderList", ))
	}*/
	
}
