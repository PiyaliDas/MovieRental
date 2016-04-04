package com.movie.rent.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class CartView {

	List<Movie>  movies;

	public CartView(List<Movie> movies) {
		super();
		this.movies = movies;
	}
	public List<Movie> getMovies(){
		return movies == null ?new ArrayList<>() :movies;
	}
	
}
