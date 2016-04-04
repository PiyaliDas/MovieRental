package com.movie.rent.RestApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.rent.model.Movie;
import com.movie.rent.service.MovieService;

@RestController
public class MovieApi {
	
	@Autowired
	MovieService movieServ;
	

	@RequestMapping("/getMovies")
	public @ResponseBody List<Movie> getMovieList(@RequestParam(required=false, name="genres") String genres){
		if(genres==null || genres.isEmpty()){
			return movieServ.getAllMovies();
		}
		else{
			return movieServ.getMovieByGenres(genres);
		}
	}
	
	@RequestMapping("/getMovie/{movie}")
	public @ResponseBody Movie getMovie(@PathVariable("movie")String movieName){
		return movieServ.getMovieDetails(movieName);		
	}
	
}
