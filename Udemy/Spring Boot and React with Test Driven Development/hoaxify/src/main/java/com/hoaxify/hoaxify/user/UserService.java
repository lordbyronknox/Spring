package com.hoaxify.hoaxify.user;

import org.springframework.stereotype.Service;

@Service							//tells Spring to create an instance of this class
public class UserService {
	
	UserRepository userRepository;
	
//Constructor injection
//when Spring creates an instance of this class it will call the constructor, so we dont need to autowire userRepository.
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	//method that returns a user object. Accepts user object as arg, which it gets from the POST request.
	public User save(User user) {
		return userRepository.save(user);
}
}
