package com.movie.rent.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.rent.model.Cart;
import com.movie.rent.model.Movie;
import com.movie.rent.model.Order;
import com.movie.rent.model.User;
import com.movie.rent.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/addToCart", method=RequestMethod.POST)
	public String addMovieToCart(@ModelAttribute Movie movie, Map<String, Object> model){
		List<Movie> movieList = orderService.addToCart(movie);
		model.put("movieList", movieList);
		return "viewAll";
	}
	
	@RequestMapping("/showCart")
	public String showCart(Map<String, Object> model){
		Cart cart = orderService.showCart();
		model.put("cart", cart);
		return "placeOrder";
	}
	
	@RequestMapping("/placeOrder")	
	public String saveOrder(Map<String, Object> model){
		String orderId = orderService.saveOrder(((User)session.getAttribute("user")).getId());
		model.put("orderId", orderId);
		return "redirect:/viewOrders";
	}
	
	@RequestMapping("/viewOrders")
	public String viewOrders(Map<String, Object> model){
		User user = (User) session.getAttribute("user");
		List<Order> orderList = orderService.getOrderByUser(user.getId());
		model.put("orderList", orderList);
		return "viewOrder";
	}
	
}
