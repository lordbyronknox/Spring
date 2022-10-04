package com.stacksimplify.restservices.Hello;

public class UserDetails {
	
	private String firstname;
	private String lastname;
	private String city;
	
	
//constructor
	public UserDetails(String firstname, String lastname, String city) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
	}
	
//getters and setters
	public String getFirstname() {
		return firstname;
	}

//toString method
	@Override
	public String toString() {
		return "UserDetails [firstname=" + firstname + ", lastname=" + lastname + ", city=" + city + "]";
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

}
