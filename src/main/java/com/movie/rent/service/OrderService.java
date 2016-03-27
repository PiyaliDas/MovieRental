package com.movie.rent.service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.rent.model.Cart;
import com.movie.rent.model.Movie;
import com.movie.rent.model.Order;
import com.movie.rent.model.User;
import com.movie.rent.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	Cart cart;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	HttpSession session;
	
	public List<Movie> addToCart(Movie movie){
		cart.addToCart(movie);
		return movieService.getAllMovies().stream().
				filter(m->{
					Iterator<Movie> itr = cart.getMovieList().iterator();
					while(itr.hasNext()){
						if(itr.next().getName().equalsIgnoreCase(m.getName()))
							return false;
					}
					return true;
						}).
				collect(Collectors.toList());
	}
	
	public Cart showCart(){
		return cart;
	}
	
	public String saveOrder(Order order){
		order.setOrderAmount();
		order.setUserId(((User) session.getAttribute("user")).getId());
		return orderRepository.save(order).getId();
	}
	
	public Order getOrderById(String id){
		return orderRepository.findOne(id);
	}

	public List<Order> getOrderByUser(String userId) {
		return orderRepository.getOrdersByUser(userId);
	}
	
}
