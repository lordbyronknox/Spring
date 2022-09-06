package com.yahoo.app.ws.ui.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yahoo.app.ws.ui.model.request.UserDetailsRequestModel;
import com.yahoo.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("/users")	
public class UserController {
	
	@GetMapping									
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="limit", defaultValue="50") int limit)		
	{														
		return "get users was called with page = " + page + " and limit = " + limit;
	}
	

	@GetMapping(
			path="/{userId}", 							      
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }  
			)																
	public ResponseEntity<UserRest> getUser(@PathVariable String userId)		
	{															
		UserRest returnValue = new UserRest();					
		returnValue.setEmail("test@test.com");					
		returnValue.setFirstName("Sergey");
		returnValue.setLastName("Kargopolov");					
		
//		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
		return new ResponseEntity<UserRest>(HttpStatus.BAD_REQUEST);
		
	}
	
	
	@PostMapping(
			consumes = { 									//can consume json & xml.
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE },
			produces = { 									//can produce json & xml.
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE }) 
	//@RequestBody lets this method read info coming in from the HTTP request body.
	//Accepts object of type UserDetailsRequestModel as an argument, assigns it to 'userDetails'.
	public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
	
		//create a returnValue object, and set its details.
		UserRest returnValue = new UserRest();					
		returnValue.setEmail(userDetails.getEmail());					
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}

	
	@PutMapping
	public String updateUser()
	{
		return "update user was called";
	}
	
	
	@DeleteMapping
	public String deleteUser()
	{
		return "delete user was called";
	}
	
}
