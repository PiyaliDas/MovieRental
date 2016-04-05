package com.movie.rent.RestApi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.rent.exception.UserNotAuthorizedException;
import com.movie.rent.exception.UserNotFoundException;
import com.movie.rent.model.User;
import com.movie.rent.service.UserService;

@RestController
public class LoginApi {

	@Autowired
	UserService userServ;	
	
	@RequestMapping("/api/login")
	@ResponseBody
	public Map<String, Object> userLogin(@RequestParam("userName") String userName, @RequestParam("password") String password){
		Map<String, Object> json = new HashMap<String, Object>();
		User user;
		try {
			user = userServ.getUser(userName);
			userServ.authenticate(user,password);
			json.put("status", HttpStatus.OK);
			json.put("user", user);
		} catch (UserNotFoundException e) {
			json.put("status", HttpStatus.NOT_FOUND);
		} catch (UserNotAuthorizedException e) {
			json.put("status", HttpStatus.UNAUTHORIZED);
		}
		return json;		
	}
}
