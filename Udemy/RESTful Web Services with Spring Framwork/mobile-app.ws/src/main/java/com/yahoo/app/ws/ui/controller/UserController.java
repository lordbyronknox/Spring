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

import com.yahoo.app.ws.exceptions.UserServiceException;
import com.yahoo.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
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
		if(true)
			throw new UserServiceException("A user service exception is thrown");
		
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
//					MediaType.APPLICATION_XML_VALUE, 
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

	
	@PutMapping(path="/{userId}",							//can receive userId as a path paramater.
			consumes = { 									//can consume json & xml.
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE },
			produces = { 									//can produce json & xml.
//			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE })
	//method gets arguments from HTTP request: userId and userDetails 
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails)
	{
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName()); 		//update user first name, using info from body of request.
		storedUserDetails.setLastName(userDetails.getLastName());			//update last name...
		
		//after details have been updated, add user to Map 'users'.
		users.put(userId, storedUserDetails);		//key= user id, value= object holding user details.
		
		return storedUserDetails;
	}
	
	
	@DeleteMapping(path="/{id}")			//maps this method to the DELETE HTTP request, an path '/< user id>'
	
	public ResponseEntity<Void> deleteUser(@PathVariable String id)	//Allows method to read the above path variable from the
	{													// HTTP request (eg. localhost:8080/users/0cc576ba-adc4-45b3-a322-e7db92553f43).
		users.remove(id);								//removes user from HashMap. (user id = key)
		return ResponseEntity.noContent().build();		//return '204 No Content' status message.
	}
	
}
