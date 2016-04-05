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

}
