package com.hoaxify.hoaxify.user;

import lombok.Data;

@Data			//lombok annotation: creates constructor, getters and setters for the fields
public class User {
	
	private String username;
	private String displayName;
	private String password;
	
	
	
}
