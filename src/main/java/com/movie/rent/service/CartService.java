package com.movie.rent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.rent.repository.MovieRentRepository;

@Service
public class CartService {

	@Autowired
	MovieRentRepository repo;
	
}
