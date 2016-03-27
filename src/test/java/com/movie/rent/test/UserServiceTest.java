package com.movie.rent.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

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
	
	/*protected User getUserFromDB(){
		Query
		return template.find
	}*/
	
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
		user.setPassword("password1");
		User userRtvd = userService.authenticate(user);
		assertNull(userRtvd);
	}
	
}
