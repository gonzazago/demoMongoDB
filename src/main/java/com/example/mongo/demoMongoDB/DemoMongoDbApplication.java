package com.example.mongo.demoMongoDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@SpringBootApplication
@EnableReactiveMongoRepositories
public class DemoMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMongoDbApplication.class, args);
	}
}
