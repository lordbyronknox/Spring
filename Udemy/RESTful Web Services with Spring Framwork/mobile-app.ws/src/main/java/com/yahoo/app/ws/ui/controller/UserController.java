package com.yahoo.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController: This registers this class as a REST controller, and will be able to receive HTTP 
//requests and match their URL path.
@RestController
//@RequestMapping: Controller responsible for alterations that relate to 'users'.
//http://localhost:8080/users
@RequestMapping("users")	
public class UserController {
	
	//create method that GETs user information.
	//@GetMapping: Binds this method to HTTP get request.
	@GetMapping
	public String getUser()
	{
		return "get user was called";
	}
	
	
	//create method to create users
	//PostMapping: Binds method to HTTP post request.
	@PostMapping
	public String createUser()
	{
		return "create user was called";
	}

	
	//create method to update user details
	//@PutMapping: Binds this method to HTTP put request.
	@PutMapping
	public String updateUser()
	{
		return "update user was called";
	}
	
	
	//create method to delete users
	//@DeleteMapping: binds this method to HTTP delete.
	@DeleteMapping
	public String deleteUser()
	{
		return "delete user was called";
	}
	
}
