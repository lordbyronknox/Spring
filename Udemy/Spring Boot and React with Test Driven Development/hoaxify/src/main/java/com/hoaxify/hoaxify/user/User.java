package com.hoaxify.hoaxify.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data			//lombok annotation: creates constructor, getters and setters for the fields
@Entity			//maps an object to a database table
public class User {
	
	@Id							//marks this as the primary key
	@GeneratedValue				//auto-generate id numbers as users are created
	private long id;
	
	private String username;
	
	private String displayName;
	
	private String password;
	
	
	
}
