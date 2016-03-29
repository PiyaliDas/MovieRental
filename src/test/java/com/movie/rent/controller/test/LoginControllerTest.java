package com.movie.rent.controller.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.movie.rent.MovieRentalApplicationTests;
import com.movie.rent.controller.LoginController;
import com.movie.rent.model.User;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class LoginControllerTest extends MovieRentalApplicationTests{

	@Autowired
	LoginController loginController;
	
	MockMvc mvc;
	
	@Autowired
	WebApplicationContext wac;
	
	@Before
	public void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testShouldAuthenticateUser() throws Exception{
		User user = new User();
		user.setName("user1");
		user.setPassword("password1");
		ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/login").
				contentType(MediaType.APPLICATION_FORM_URLENCODED).
				param("name", "user1").
				param("password", "password1").
				sessionAttr("user", new User()));
		result.andExpect(status().is3xxRedirection()).
		andExpect(view().name("redirect:/getAllMovies"));
	}
	
	@Test
	public void testShouldNotAuthenticateUser() throws Exception{
		User user = new User();
		user.setName("user1");
		user.setPassword("password1");
		ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/login").
				contentType(MediaType.APPLICATION_FORM_URLENCODED).
				param("name", "user1").
				param("password", "password2").
				sessionAttr("user", new User()));
		result.andExpect(status().is2xxSuccessful()).
		andExpect(view().name("login"));
	}
}
