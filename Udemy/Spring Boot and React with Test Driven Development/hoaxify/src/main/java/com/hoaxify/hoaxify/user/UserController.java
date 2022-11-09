package com.hoaxify.hoaxify.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.hoaxify.error.ApiError;
import com.hoaxify.hoaxify.shared.GenericResponse;

@RestController						//makes this class has methods responsible for handling http requests.
public class UserController {
	
	@Autowired					//inject the userService
	UserService userService;
	
//method to save user object to database	
//@RequestBody: get the body of incoming request, User: type of object we are waiting for. (Spring converts body from Json to User object.
	@PostMapping("/api/1.0/users")
	GenericResponse createUser(@Valid @RequestBody User user) {		
		userService.save(user);
		return new GenericResponse("User saved");		
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)			//tell spring to return the BAD_REQUEST status code 
	ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
		ApiError apiError = new ApiError(400, "Validation error", request.getServletPath());
		
		BindingResult result = exception.getBindingResult();
		
		Map<String, String> validationErrors = new HashMap<>();
		for(FieldError fieldError: result.getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		apiError.setValidationErrors(validationErrors);
		return apiError;
	}

}
