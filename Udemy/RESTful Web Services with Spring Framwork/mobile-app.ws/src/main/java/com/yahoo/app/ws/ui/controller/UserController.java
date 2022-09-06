package com.yahoo.app.ws.ui.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yahoo.app.ws.ui.model.response.UserRest;

//@RestController: This registers this class as a REST controller, and will be able to receive HTTP 
//requests and match their URL path.
@RestController
//@RequestMapping: Controller responsible for alterations that relate to 'users'.
//http://localhost:8080/users	<- '/users' path added to annotation.
@RequestMapping("/users")	
public class UserController {
	
//create method that GETs all users.
//@GetMapping: Binds this method to HTTP GET request.
//Get Request with parameters eg.: http://localhost:8080/users?page=1&limit=50
//@RequestParam(value="<something>") int page,... - maps the methods parameter to the parameter passed in the HTTP request.
//optional - add a default value: @RequestParam(value="page", defaultValue="1")
	@GetMapping									
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="limit", defaultValue="50") int limit)		
	{														
		return "get users was called with page = " + page + " and limit = " + limit;
	}
	

//create method that GETs users by ID.
	@GetMapping(
			path="/{userId}", 							       //path added to binding	so method can read the userId. (/users/userId)
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }  //produces = { MediaType... } = allows return in JSON and XML format.
			)																
	public ResponseEntity<UserRest> getUser(@PathVariable String userId)		//PathVariabe that is read from url users/userId will be made available to
	{															// the methods via an argument. i.e. The user's id
		UserRest returnValue = new UserRest();					//Create instance of UserRest
		returnValue.setEmail("test@test.com");					//set values of the object.
		returnValue.setFirstName("Sergey");
		returnValue.setLastName("Kargopolov");					//
		//ResponseEntity() constructor takes various args depending on the response you want. 
		// Here we return 'body' (returnValue), and status.
//		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
		return new ResponseEntity<UserRest>(HttpStatus.BAD_REQUEST);
		
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
