package com.movie.rent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MovieRentalApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class MovieRentalApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
}
