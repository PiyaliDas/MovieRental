package com.movie.rent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.rent.model.User;
import com.movie.rent.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
		
	public User authenticate(User user) {
		User userRtvd = userRepo.getUserByName(user.getName());
		if(userRtvd!=null && userRtvd.getPassword().equals(user.getPassword())){			
			return userRtvd;
		}
		else{
			return null;
		}
	}
	
}
