package com.movie.rent.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.movie.rent.model.Movie;

@Component
public class MovieRepository {

	@Autowired
	MongoTemplate template;
	
	public List<Movie> getAllMovies(){
		return template.findAll(Movie.class);
	}
	
	public Movie getMovieDetails(String name){
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return template.findOne(query, Movie.class);
	}
}
