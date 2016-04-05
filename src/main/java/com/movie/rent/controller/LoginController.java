package com.movie.rent.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.rent.exception.UserNotAuthorizedException;
import com.movie.rent.exception.UserNotFoundException;
import com.movie.rent.model.User;
import com.movie.rent.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String userLogin(@ModelAttribute("user") User user){
		try{
			User userRtvd = userService.getUser(user.getName());
			userService.authenticate(userRtvd, userRtvd.getPassword());
			session.setAttribute("user", userRtvd);
			return "redirect:/getAllMovies";
		}
		catch(UserNotFoundException e){
			
		}
		catch(UserNotAuthorizedException e){
			
		}
		return "login";
	}
	
	@RequestMapping("/logout")
	public String userLogout(){
		session.invalidate();
		return "login";
	}
	
	
}
