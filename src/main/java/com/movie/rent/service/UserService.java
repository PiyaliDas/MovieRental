package com.movie.rent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.rent.exception.UserNotAuthorizedException;
import com.movie.rent.exception.UserNotFoundException;
import com.movie.rent.model.User;
import com.movie.rent.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	public User getUser(String userName) throws UserNotFoundException{
		User userRtvd = userRepo.getUserByName(userName);
		if(userRtvd==null)
			throw new UserNotFoundException();
		return userRtvd;
	}
	public boolean authenticate(User user, String password) throws UserNotAuthorizedException {
		if(!user.authenticate(password))			
			throw new UserNotAuthorizedException();
		return true;
	}
	
}
