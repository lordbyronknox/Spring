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
	
//method to save user object to database	
//@RequestBody: get the body of incoming request, User: type of object we are waiting for. (Spring converts body from Json to User object.
	@PostMapping("/api/1.0/users")
	GenericResponse createUser(@RequestBody User user) {	
		userService.save(user);
		return new GenericResponse("User saved");
		
	}

}
