package com.movie.rent.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.rent.model.Cart;
import com.movie.rent.model.Movie;
import com.movie.rent.model.Order;
import com.movie.rent.model.User;
import com.movie.rent.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/addToCart")
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
	public String saveOrder(@ModelAttribute("order") Order order, Map<String, Object> model, HttpSession session){
		String orderId = orderService.saveOrder(order);
		model.put("orderId", orderId);
		return "redirect:/viewOrders?userId=" + ((User)session.getAttribute("user")).getId();
	}
	
	@RequestMapping("/viewOrders")
	public String viewOrders(@RequestParam("userId") String userId, Map<String, Object> model){
		List<Order> orders = orderService.getOrderByUser(userId);
		model.put("orderList", orders);
		return "viewOder";
	}
	
}
