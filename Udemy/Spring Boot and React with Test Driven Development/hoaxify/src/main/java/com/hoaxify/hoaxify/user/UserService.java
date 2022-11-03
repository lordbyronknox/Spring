package com.hoaxify.hoaxify.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service							//tells Spring to create an instance of this class
public class UserService {
	
	UserRepository userRepository;
	
	BCryptPasswordEncoder passwordEncoder;	//initialize the Spring security dependency encoder
	
//Constructor injection: when Spring creates an instance of this class it will call the constructor, the constructor is looking 
//	for 'userRepository	so it provides the userRepository to this service, and we dont need to autowire it in.
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();	
	}
	
	//method that returns a user object. Accepts user object as arg, which it gets from the POST request.
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));	//encode the password and set it as the user password.
		return userRepository.save(user);
}
}
