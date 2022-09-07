package com.yahoo.app.ws.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDetailsRequestModel {
	
	    //@NotNull - Stipulates that field cannot be null. Returns error message if field is left out.
		@NotNull(message="First name connot be null")
		@Size(min=2, message="First name cant be less than 2 characters.")
		private String firstName;						
		
		@NotNull(message="Last name connot be null")
		@Size(min=2, message="Last name cant be less than 2 characters.")
		private String lastName;

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

}
