package com.hoaxify.hoaxify.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.hoaxify.shared.GenericResponse;

@RestController						//makes this class has methods responsible for handling http requests.
public class UserController {
	
	@Autowired					//inject the userService
	UserService userService;
	
	@PostMapping("/api/1.0/users")
	GenericResponse createUser(@RequestBody User user) {	//method to save user object to database
		userService.save(user);
		GenericResponse body = new GenericResponse();
		body.setMessage("User saved");
		return body;
		
	}

}
