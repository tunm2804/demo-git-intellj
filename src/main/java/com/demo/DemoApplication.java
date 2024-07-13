package com.demo;

import com.demo.repo.CategoryRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		var ctx = SpringApplication.run(DemoApplication.class, args);
//		CategoryRepo repo = ctx.getBean(CategoryRepo.class);
//		System.out.println(repo.findAll());
	}

}
