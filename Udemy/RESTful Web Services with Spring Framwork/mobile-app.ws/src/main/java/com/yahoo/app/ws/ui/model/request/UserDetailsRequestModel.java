//class to create objects that can hold the HTTP request's json data.

package com.yahoo.app.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDetailsRequestModel {
	
	//@NotNull - Stipulates that field cannot be null. Returns error message if field is left out.
	@NotNull(message="First name connot be null")
	@Size(min=2, message="First name cant be less than 2 characters.")
	private String firstName;						
	
	@NotNull(message="Last name connot be null")
	@Size(min=2, message="Last name cant be less than 2 characters.")
	private String lastName;
	
	@NotNull(message="Email name connot be null")
	//@Email	Ensures that the input is an email
	@Email
	private String email;
	
	@NotNull(message="Password name connot be null")
	//@Size lets you specify the password's length.
	@Size(min=8,max=16, message="Password must be between 8 and 16 characters.") 
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
