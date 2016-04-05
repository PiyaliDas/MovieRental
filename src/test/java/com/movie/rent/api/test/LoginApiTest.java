package com.movie.rent.api.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.movie.rent.MovieRentalApplicationTests;

public class LoginApiTest extends MovieRentalApplicationTests{

	MockMvc mvc;
	
	@Autowired
	WebApplicationContext wac;
	
	@Before
	public void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testShouldAuthenticateUser() throws Exception{
		String userName = "user1";
		String password = "password1";
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/api/login?userName=".concat(userName).concat("&password=").concat(password)).accept(MediaType.APPLICATION_JSON));
		result.andExpect(jsonPath("$.status", Matchers.is(HttpStatus.OK.name()))).andExpect(jsonPath("$.user.name", Matchers.is("user1")));
	}
	
	@Test
	public void testShouldReturnUserNotFound() throws Exception{
		String userName = "user2";
		String password = "password1";
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/api/login?userName=".concat(userName).concat("&password=").concat(password)).accept(MediaType.APPLICATION_JSON));
		result.andExpect(jsonPath("$.status", Matchers.is(HttpStatus.NOT_FOUND.name())));
	}
	
	@Test
	public void testShouldReturnUserNotAuthenticated() throws Exception{
		String userName = "user1";
		String password = "password2";
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/api/login?userName=".concat(userName).concat("&password=").concat(password)).accept(MediaType.APPLICATION_JSON));
		result.andExpect(jsonPath("$.status", Matchers.is(HttpStatus.UNAUTHORIZED.name())));
	}
}
