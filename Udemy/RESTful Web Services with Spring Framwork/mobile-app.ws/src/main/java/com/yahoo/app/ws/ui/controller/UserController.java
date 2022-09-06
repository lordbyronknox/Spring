package com.yahoo.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

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
	
	//Initialize a Map, with String type key, and UserRest type value.
	Map<String, UserRest> users;
	
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
		//Checks if the request contains a userId that exists in 'users' HashMap and returns the user details and 'OK' if true.
		//If false, returns NO_CONTENT message.
		if(users.containsKey(userId))
		{
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@PostMapping(
			consumes = { 									//can consume json & xml.
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE },
			produces = { 									//can produce json & xml.
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE }) 

	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails)
	{

		UserRest returnValue = new UserRest();					
		returnValue.setEmail(userDetails.getEmail());					
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		//generate a random user ID
		String userId = UUID.randomUUID().toString();
		//Assign the 'userId' value to the 'returnValue' object's userId property.
		returnValue.setUserId(userId);
		
		//if there are no users, create a HashMap,  and add the user to it.
		if (users == null) users = new HashMap<>();		
		users.put(userId, returnValue);
		
		
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
