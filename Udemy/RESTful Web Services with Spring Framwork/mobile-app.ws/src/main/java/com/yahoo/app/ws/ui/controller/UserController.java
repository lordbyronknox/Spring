package com.yahoo.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController: This registers this class as a REST controller, and will be able to receive HTTP 
//requests and match their URL path.
@RestController
//@RequestMapping: Controller responsible for alterations that relate to 'users'.
//http://localhost:8080/users	<- '/users' path added to annotation.
@RequestMapping("/users")	
public class UserController {
	
//create method that GETs all users.
//@GetMapping: Binds this method to HTTP GET request.
	@GetMapping									
	public String getUsers(@RequestParam(value="page") int page, @RequestParam(value="limit") int limit)		
	{														
		return "get users was called with page = " + page + " and limit = " + limit;
	}
	

//create method that GETs users by ID.
	@GetMapping(path="/{userId}")							//path added to binding	so method can read the userId. (/users/userId)								
	public String getUser(@PathVariable String userId)		//PathVariabe that is read from url users/userId will be made available to
	{														// the methods via an argument. i.e. The user's id
		return "get user was called: " + userId;
	}
	
	
//create method to create users
//PostMapping: Binds method to HTTP POST request.
	@PostMapping
	public String createUser()
	{
		return "create user was called";
	}

	
//create method to update user details
//@PutMapping: Binds this method to HTTP PUT request.
	@PutMapping
	public String updateUser()
	{
		return "update user was called";
	}
	
	
//create method to delete users
//@DeleteMapping: binds this method to HTTP DELETE request.
	@DeleteMapping
	public String deleteUser()
	{
		return "delete user was called";
	}
	
}
