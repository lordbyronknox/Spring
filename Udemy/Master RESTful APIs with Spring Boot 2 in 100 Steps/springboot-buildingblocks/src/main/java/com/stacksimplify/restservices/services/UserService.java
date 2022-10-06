package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

//Service
@Service
public class UserService {

	// Autowire the UserRepository
	@Autowired
	private UserRepository userRepository;

	// getAllUsers Method
	public List<User> getAllUsers() {

		return userRepository.findAll();

	}
	
	//CreateUser Method
	public User createUser(User user) throws UserExistsException {
		//check if user exists, using username
		//create User object 'existingUser'
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		//if user exists, throw exception.
		if (existingUser != null)
			throw new UserExistsException("User already exists in repository.");
		//else save user to repo.
		return userRepository.save(user);
	}
	
	//getUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException {	//add 'throws UserNotFoundException'
		Optional<User> user = userRepository.findById(id);
		if( !user.isPresent()) {												//if user not found throw "user not..."
			throw new UserNotFoundException("User not found in user repository.");
		}
		return user;
	}


	//updateUserById
	public User updateUserById(Long id, User user) throws UserNotFoundException {	//add throws exception class
		
		Optional<User> optionalUser = userRepository.findById(id);
		if( !optionalUser.isPresent()) {								//if user not found throw "user not..."
			throw new UserNotFoundException("User not found in user repository. Provide correct user ID.");
		}
		user.setId(id);									//calls the User setId() method.
		return userRepository.save(user);				//save the change to the repository.
		
	}
	
	
	//deleteUserById
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if( !optionalUser.isPresent()) {								//if user not found throw "user not..."
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in user repository. Provide correct user ID.");
		}	
			userRepository.deleteById(id);								//...otherwise, delete the user.	

	}
	
	
	//getUserByUsername
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);		//
	}
	

}