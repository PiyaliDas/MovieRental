package com.movie.rent.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.rent.model.Movie;
import com.movie.rent.service.MovieService;


@Controller
public class MovieController {

	@Autowired
	private MovieService service;
	
	@RequestMapping("/getAllMovies")
	public String listMoviesAll(Map<String, Object> model,@RequestParam(required=false) String genre){
		List<Movie> movieList = service.getAllMovies();
		model.put("movieList", movieList);
		return "viewAll";
	}
	
	@RequestMapping("/getMoviesByGenres")
	public String getMoviesByGenres(@RequestParam("genres") String genres,Map<String, Object> model){
		List<Movie> movieList = service.getMovieByGenres(genres);
		model.put("movieList", movieList);
		return "viewAll";
	}
	
	@RequestMapping("/getMovieDetails")
	public String getMovieDetails(@RequestParam("movie") String movieId, Map<String, Object> model){
		Movie movie = service.getMovieDetails(movieId);
		model.put("movie", movie);
		return "movieDetails";
	}
	
}
