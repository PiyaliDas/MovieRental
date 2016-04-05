package com.movie.rent.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.model.Movie;
import com.movie.rent.service.MovieService;
import static org.junit.Assert.*;

public class MovieServiceTest extends MovieRentalApplicationTests{

	@Autowired
	MongoTemplate template;
	
	@Autowired
	MovieService movieService;
	
	private void dataSetup(){
		template.dropCollection(Movie.class);
		Movie movie1 = new Movie("name1", new Date(), "actor1", "actress1", "drama", 100);
		template.save(movie1, "movie");
		Movie movie2 = new Movie("name2", new Date(), "actor2", "actress2", "mystrey", 100);
		template.save(movie2, "movie");
	}
	
	@Test
	public void testGetAllMovies(){
		dataSetup();
		List<Movie> movieList = movieService.getAllMovies();
		assertEquals(movieList.stream().count(),2);
	}
	
	@Test
	public void testGetMovieByGenres(){
		dataSetup();
		List<Movie> filteredList = movieService.getMovieByGenres("drama");
		assertEquals(filteredList.stream().count(),1);
	}
	
	@Test
	public void testGetMovieDetails(){
		template.dropCollection(Movie.class);
		Movie movie1 = new Movie("name1", new Date(), "actor1", "actress1", "drama", 100);
		template.save(movie1, "movie");
		Movie movie2 = new Movie("name2", new Date(), "actor2", "actress2", "mystrey", 100);
		template.save(movie2, "movie");
		Movie movie = movieService.getMovieDetails(movie1.getId());
		assertEquals(movie.getName(), "name1");
		//assertEquals(movie.getReleaseDate(),new Date());
		assertEquals(movie.getActor(), "actor1");
		assertEquals(movie.getActress(), "actress1");
		assertEquals(movie.getGenres(), "drama");
		assertEquals("Assert double", movie.getPrice(),100,0);
	}
	
}
