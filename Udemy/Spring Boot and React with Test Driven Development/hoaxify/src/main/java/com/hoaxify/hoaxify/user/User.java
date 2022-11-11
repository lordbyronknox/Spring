package com.hoaxify.hoaxify.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

import lombok.Data;

@Data			//lombok annotation: creates constructor, getters and setters for the fields
@Entity			//maps an object to a database table
public class User {
	
	@Id							//marks this as the primary key
	@GeneratedValue				//hibernate auto-generates id numbers as users are created
	private long id;
	
	@NotNull(message = "{hoaxify.constraints.username.NotNull.message}")			
	@Size(min = 4, max = 255)
	@UniqueUsername
	private String username;
	
	@NotNull
	@Size(min = 4, max = 255)
	private String displayName;
	
	@NotNull
	@Size(min = 8, max = 255)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message="{hoaxify.constraints.password.Pattern.message}")
	private String password;
	
	
	
}
