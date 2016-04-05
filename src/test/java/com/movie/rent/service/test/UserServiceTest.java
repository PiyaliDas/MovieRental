package com.movie.rent.service.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testng.annotations.ExpectedExceptions;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.exception.UserNotAuthorizedException;
import com.movie.rent.model.User;
import com.movie.rent.service.UserService;

public class UserServiceTest extends MovieRentalApplicationTests{

	@Autowired
	private MongoTemplate template;
	
	@Autowired
	private UserService userService;
	
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
	public void testShouldAuthenticateUser() throws UserNotAuthorizedException{
		addUserToDB();
		User user = new User();
		user.setName("user1");
		user.setPassword("password1");
		assertTrue(userService.authenticate(user,user.getPassword()));

	}
	
	@Test
	@ExpectedExceptions(UserNotAuthorizedException.class)
	public void testShouldNotAuthenticateUser() throws UserNotAuthorizedException{
		addUserToDB();
		User user = new User();
		user.setName("user1");
		user.setPassword("password2");
		userService.authenticate(user,user.getPassword());

	}
	
}
