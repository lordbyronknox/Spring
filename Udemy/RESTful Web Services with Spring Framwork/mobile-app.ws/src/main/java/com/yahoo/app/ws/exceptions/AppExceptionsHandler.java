package com.yahoo.app.ws.exceptions;


import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yahoo.app.ws.ui.model.response.ErrorMessage;

//@ControllerAdvice: Registers this class with the framework, and allows it to listen for exceptions
// that occur in the UserController methods.
@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

	//Method that handles general exceptions (from UserController methods).
	//@ExceptionHandler allows this method to handle the exception.
	//{Exception.class} = can handle exceptions of the Exception class. 
	//...(Exception ex, WebRequest request) = accepts arguments of these types.
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) 
	{
		
		//return the custom error class (ErrorMessage.java):
		//  check if error message is null, if so return description of error, assign to 'errorMessageDescription'.
		//	create an instance of ErrorMessage, constructor takes Date and message as args.
		//  return ResponseEntity object that holds the 
		String errorMessageDescription = ex.getLocalizedMessage();
		if(errorMessageDescription == null)
			errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		//returns a ResponseEntity object that contains body, headers, error.
		return new ResponseEntity<> (errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//method that handles NullPointerException
	@ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
	public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest request) 
	{
		
		String errorMessageDescription = ex.getLocalizedMessage();
		if(errorMessageDescription == null)
			errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		//returns a ResponseEntity object that contains body, headers, error.
		return new ResponseEntity<> (errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
/*	//method that handles UserServiceException
		@ExceptionHandler(value = {UserServiceException.class})
		public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) 
		{
			
			String errorMessageDescription = ex.getLocalizedMessage();
			if(errorMessageDescription == null)
				errorMessageDescription = ex.toString();
			
			ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
			//returns a ResponseEntity object that contains body, headers, error.
			return new ResponseEntity<> (errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
*/
}
