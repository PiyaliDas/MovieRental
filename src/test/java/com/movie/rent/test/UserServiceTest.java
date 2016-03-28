package com.movie.rent.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.model.User;
import com.movie.rent.service.UserService;

public class UserServiceTest extends MovieRentalApplicationTests{

	@Autowired
	MongoTemplate template;
	
	@Autowired
	UserService userService;
	
	protected void addUserToDB(){
		User user = new User();
		user.setName("user1");
		user.setPassword("password1");
		template.dropCollection(User.class);
		template.save(user);
	}
	
	protected User getUserFromDB(String name){
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return (User) template.findOne(query, User.class);
	}
	
	@Test
	public void testShouldAuthenticateUser(){
		addUserToDB();
		User user = new User();
		user.setName("user1");
		user.setPassword("password1");
		User userRtvd = userService.authenticate(user);
		assertTrue(user.getName().equals(userRtvd.getName()));
		assertTrue(user.getPassword().equals(userRtvd.getPassword()));

	}
	
	@Test
	public void testShouldNotAuthenticateUser(){
		addUserToDB();
		User user = new User();
		user.setName("user1");
		user.setPassword("password2");
		User userRtvd = userService.authenticate(user);
		assertNull(userRtvd);
	}
	
}
