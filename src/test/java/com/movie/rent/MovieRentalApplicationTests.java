package com.movie.rent;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MovieRentalApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class MovieRentalApplicationTests {

	@Autowired
	MongoTemplate template;
	
	@Test
	public void contextLoads() {
	}
	
	protected <T> void DataSetup(List<T> list,Class<T> c){
		//template.
		template.insert(list, c);
	}

}
