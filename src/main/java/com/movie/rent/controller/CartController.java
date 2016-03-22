package com.movie.rent.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.rent.model.Cart;
import com.movie.rent.model.Movie;
import com.movie.rent.service.MovieListService;

@Controller
public class CartController {
	
	@Autowired
	Cart cart;

	@Autowired
	MovieListService movieListService;
	
	@RequestMapping("/addToCart")
	public String addMovieToCart(@ModelAttribute Movie movie, Map<String, Object> model){
		cart.addToCart(movie);
		List<Movie> movieList = movieListService.getAllMovies();
		//todo: logic is wrong need to consider all the movies in cart
		movieList = movieList.stream().filter(m -> m.getName().equalsIgnoreCase(movie.getName())?false:true).collect(Collectors.toList());
		model.put("movieList", movieList);
		return "viewAll";
	}
	
	@RequestMapping("/showCart")
	public String showCart(Map<String, Object> model){
		model.put("cart", cart);
		return "placeOrder";
	}
}
