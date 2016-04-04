package com.movie.rent.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Cart{

	private List<Movie> movieList;


	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}
	
	public void addToCart(Movie movie){
		if(movieList==null){
			movieList = new ArrayList<>();
		}
		movieList.add(movie);
	}
	
	/*public void removeFromCart(Movie movie){
		if(movieList!=null){
			movieList = movieList.stream().filter(m -> !m.getName().equalsIgnoreCase(movie.getName())).collect(Collectors.toList());
		}
	}*/
}
