package com.databaseconnect.SpringDBplay;

import com.databaseconnect.SpringDBplay.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDBplayApplication {

	@Autowired
	private ProductsRepository productsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDBplayApplication.class, args);
	}

}
