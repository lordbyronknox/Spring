package com.hoaxify.hoaxify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//	Spring Boot does auto config. for the dependencies. Spring security's default config is securing all end-points - so only authorized
//  requests are processed. so until we add authentication to the requests we 'exclude' the Security configuration.  
public class HoaxifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoaxifyApplication.class, args);
	}

}
