package com.movie.rent.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.rent.model.Movie;
import com.movie.rent.repository.MovieRentRepository;

@Service
public class MovieService {

	@Autowired
	MovieRentRepository repository;
	
	public List<Movie> getAllMovies(){
		List<Movie> movies = repository.getAllMovies();
		return movies;
	}
	
	public List<Movie> getMovieByGenres(final String genres){
		List<Movie> movies = repository.getAllMovies();
		List<Movie> refinedList = movies.stream().filter((movie) -> movie.getGenres().equalsIgnoreCase(genres)).collect(Collectors.toList());
		return refinedList;
	}
	
	public Movie getMovieDetails(String name){
		return repository.getMovieDetails(name);
	}
}
