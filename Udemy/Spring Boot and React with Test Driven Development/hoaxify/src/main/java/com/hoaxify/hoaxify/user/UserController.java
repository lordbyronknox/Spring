package com.hoaxify.hoaxify.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController						//makes this class has methods responsible for handling http requests.
public class UserController {
	
	@PostMapping("/api/1.0/users")
	void createUser() {
		
	}

}
