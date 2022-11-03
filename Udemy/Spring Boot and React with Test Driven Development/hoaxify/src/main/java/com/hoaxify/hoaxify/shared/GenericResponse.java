package com.hoaxify.hoaxify.shared;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data								//lombok annotation that creates getters and setters
@NoArgsConstructor					//lombok annotation: creates no argument constructor
public class GenericResponse {
	
	 private String message;
	 
	 public GenericResponse(String message) {
		 this.message = message;
	 }

}
