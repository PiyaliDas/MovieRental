package com.movie.rent.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.model.Cart;
import com.movie.rent.model.Movie;
import com.movie.rent.model.Order;
import com.movie.rent.service.MovieService;
import com.movie.rent.service.OrderService;
import static org.junit.Assert.*;


public class OrderServiceTest extends MovieRentalApplicationTests{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MovieService movieService;
	
	private void setUpMovieData(){
		mongoTemplate.dropCollection(Movie.class);
		Movie movie1 = new Movie("name1", new Date(), "actor1", "actress1", "drama", 100);
		mongoTemplate.save(movie1, "movie");
		Movie movie2 = new Movie("name2", new Date(), "actor2", "actress2", "mystrey", 100);
		mongoTemplate.save(movie2, "movie");
		Movie movie3 = new Movie("name3", new Date(), "actor3", "actress3", "action", 100);
		mongoTemplate.save(movie3, "movie");
		Movie movie4 = new Movie("name4", new Date(), "actor4", "actress4", "horror", 100);
		mongoTemplate.save(movie4, "movie");
		Movie movie5 = new Movie("name5", new Date(), "actor5", "actress5", "mystrey", 100);
		mongoTemplate.save(movie5, "movie");
	}
	
	@Test
	public void testShouldReturnEmptyCart(){
		Cart cart = orderService.showCart();
		assertNull(cart.getMovieList());
	}
	
	@Test
	public void testShouldReturnOneMovieInCart(){
		setUpMovieData();
		List<Movie> movieListAll = movieService.getAllMovies();
		List<Movie> movieListRemn = orderService.addToCart(movieListAll.get(0));
		Cart cart = orderService.showCart();
		assertEquals(movieListRemn.size(),4);
		assertEquals(cart.getMovieList().size(),1);
		assertEquals(cart.getMovieList().get(0),movieListAll.get(0));
	}
	
	@Test
	public void testShouldReturnTwoMoviesInCart(){
		setUpMovieData();
		List<Movie> movieListAll = movieService.getAllMovies();
		List<Movie> movieListRemn1 = orderService.addToCart(movieListAll.get(0));
		List<Movie> movieListRemn2 = orderService.addToCart(movieListRemn1.get(0));
		Cart cart = orderService.showCart();
		assertEquals(movieListRemn2.size(),3);
		assertEquals(cart.getMovieList().size(),2);
		assertTrue(cart.getMovieList().contains(movieListAll.get(0)));
		assertTrue(cart.getMovieList().contains(movieListRemn1.get(0)));
	}
	
	@Test
	public void testShouldSaveOrder(){
		setUpMovieData();
		List<Movie> movieListAll = movieService.getAllMovies();
		List<Movie> movieListRemn1 = orderService.addToCart(movieListAll.get(0));
		orderService.addToCart(movieListRemn1.get(0));
		Order order = new Order();
		order.setMovieList(orderService.showCart().getMovieList());
		order.setRentDate(new Date());
		order.setReturnDate(new Date());
		order.setOrderAmount();
		String orderId = orderService.saveOrder(order);
		assertEquals(orderId, order.getId());
	}
	
	@Test
	public void testShouldGetOrderByIb(){
		setUpMovieData();
		List<Movie> movieListAll = movieService.getAllMovies();
		List<Movie> movieListRemn1 = orderService.addToCart(movieListAll.get(0));
		orderService.addToCart(movieListRemn1.get(0));
		Order order = new Order();
		order.setMovieList(orderService.showCart().getMovieList());
		order.setRentDate(new Date());
		order.setReturnDate(new Date());
		order.setOrderAmount();
		String orderId = orderService.saveOrder(order);
		Order orderRtrvd = orderService.getOrderById(orderId);
		assertEquals(order.getMovieList().size(),orderRtrvd.getMovieList().size());
		order.getMovieList().forEach(m->{
			assertTrue(orderRtrvd.getMovieList().
					stream().
					anyMatch(m1->m1.getName().equalsIgnoreCase(m.getName())));
		});
	}
}
