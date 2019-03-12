package com.forum.publicforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.forum.publicforum.model")
public class PublicForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicForumApplication.class, args);
	}

}
