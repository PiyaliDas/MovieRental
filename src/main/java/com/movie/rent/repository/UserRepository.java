package com.movie.rent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.movie.rent.model.User;

public interface UserRepository extends MongoRepository<User, String>{

	@Query("{'name': ?0}")
	User getUserByName(String name);
}
