package com.stacksimplify.restservices.exceptions;

public class UserExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8966650283572035376L;	//add 'generated serial version id' (hover over class name for options)

	//constructor, calls super class (Exception) which generates the message.
	public UserExistsException(String message) {						
		super(message);
		
	}

}
