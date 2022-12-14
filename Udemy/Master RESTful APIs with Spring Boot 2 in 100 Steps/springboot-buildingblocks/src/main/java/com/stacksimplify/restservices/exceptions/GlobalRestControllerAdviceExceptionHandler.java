package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)					//to get the error/exception msg
	public CustomErrorDetails usernameNotFound(UserNameNotFoundException ex) {
		
		return new CustomErrorDetails(new Date(), "From @RestCont", ex.getMessage());
	}
}
