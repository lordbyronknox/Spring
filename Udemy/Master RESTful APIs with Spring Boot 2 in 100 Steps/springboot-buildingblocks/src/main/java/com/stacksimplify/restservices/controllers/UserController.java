package com.stacksimplify.restservices.controllers;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

//Controller: @RestController to tell Spring the type of controller it is
@RestController
public class UserController {
	
	//Autowire the UserService (creates an instance of the UserService class (called 'userService') so 
	// this class can run independently of the UserService class).
	@Autowired
	private UserService userService;
	
	//getAllUsers Method
	@GetMapping("/users")
	public List<User> getAllUsers() {
		
		return userService.getAllUsers();
		
	}
	
	//Create User Method
	//@RequestBody Annotation	- gets data from body.
	//@PostMapping Annotation	- maps this method to the /users url extension.
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {	//change to void, add UriComponentsbuilder to args
		try {
			userService.createUser(user);															//create user
			//Header = header tab on postman
			HttpHeaders headers = new HttpHeaders();												//create HttpHeaders object
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());	//sets location  of object (eg. /users/1)
			return new ResponseEntity<Void> (headers, HttpStatus.CREATED);
		}
		catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	
	//getUserById
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		
		try {
			return userService.getUserById(id);
		}
		catch (UserNotFoundException ex) {
			//NOT_FOUND = 404.   ex.message = "User not found in user Repository" - defined in UserService.getUserById()
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage()); 
		}																			  
		
		
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {																		//add try/catch
			return userService.updateUserById(id, user);
		}
		catch (UserNotFoundException ex) {					//if exception is caught, throw a response message
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage()); //Bad_REQUEST = 400
		}
	}
	
	
	//deleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	
	//getUserByUsername
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
}