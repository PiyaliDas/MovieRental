package com.movie.rent.RestApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.rent.model.Cart;
import com.movie.rent.model.CartView;
import com.movie.rent.model.Movie;
import com.movie.rent.service.OrderService;

@RestController
public class OrderApi {

	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/api/addToCart", method=RequestMethod.PUT)
	public void addToCart(@RequestBody Movie movie){
		orderService.addToCart(movie);
	}
	
	@RequestMapping("/api/showCart")
	@ResponseBody
	public CartView showCart(){
		return  new CartView(orderService.showCart().getMovieList());
	}
}
