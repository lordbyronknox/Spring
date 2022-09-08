package com.yahoo.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yahoo.app.ws.shared.Utils;
import com.yahoo.app.ws.ui.model.request.UserDetailsRequestModel;
import com.yahoo.app.ws.ui.model.response.UserRest;
import com.yahoo.app.ws.userservice.UserService;

//@Service makes this class available to other classes that use @Autowire to inject an 
// instance of 'UserService'.
@Service
public class UserServiceImpl implements UserService {

	Map<String, UserRest> users;
	
	Utils utils;
	
	public UserServiceImpl() {}
	
	//Constructor - inject utils class into argument
	@Autowired
	public UserServiceImpl(Utils utils)
	{
		this.utils = utils;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest();					
		returnValue.setEmail(userDetails.getEmail());					
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		//generate a random user ID
		String userId = utils.generateUserId();
		//Assign the 'userId' value to the 'returnValue' object's userId property.
		returnValue.setUserId(userId);
		
		//if there are no users, create a HashMap,  and add the user to it.
		if (users == null) users = new HashMap<>();		
		users.put(userId, returnValue);
		
		return returnValue;
	}

}
