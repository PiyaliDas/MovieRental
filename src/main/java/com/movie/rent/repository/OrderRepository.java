package com.movie.rent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.movie.rent.model.Order;

@Component
public interface OrderRepository extends MongoRepository<Order, String>{

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
