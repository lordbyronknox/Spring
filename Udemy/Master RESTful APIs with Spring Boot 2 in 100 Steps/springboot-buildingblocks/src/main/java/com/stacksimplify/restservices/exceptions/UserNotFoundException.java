package com.stacksimplify.restservices.exceptions;

public class UserNotFoundException extends Exception {		//class to handle exceptions, extends Exception class.

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	//??????

	public UserNotFoundException(String message) {
		super(message);
		
	}

}
