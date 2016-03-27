package com.movie.rent.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.movie.rent.model.Order;

@Component
public interface OrderRepository extends MongoRepository<Order, String>{

	@Query("{'userId': ?0}")
	public List<Order> getOrdersByUser(String userId);
	
	/*public String saveOrder(Order order){
		//order.setMovieList(cart.getMovieList());
		//order.setOrderAmount();
		//order.setRentDate(Calendar.getInstance().getTime());
		//order.setReturnDate(new Date());
		template.save(order);
		return order.getId();
	}
	
	public Order getOrderById(String id){
		return template.findById(id, Order.class);
	}*/
}
