	package com.stacksimplify.restservices.entities;	
	import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;
	
	
	//Entity (in this case) is the table.
	@Entity  
	@Table(name = "users")		//@Table name defaults to the class name ('User'). To specify a different name use (name = "<name>")
	public class User extends RepresentationModel<User> {
	
		@Id
		@GeneratedValue
		private Long userid;
	
		@NotEmpty(message = "Username is a Mandatory field. Please provide username")	//@NotEmpty: Validation for column
		@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
		private String username;
	
		@Size(min=2, message = "FirstName should have at least 2 characters.")			//size validation
		@Column(name = "FIRST_NAME", length = 50, nullable = false)
		private String firstname;
	
		@Column(name = "LAST_NAME", length = 50, nullable = false)
		private String lastname;
	
		@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
		private String email;
	
		@Column(name = "ROLE", length = 50, nullable = false)
		private String role;
	
		@Column(name = "SSN", length = 50, nullable = false, unique = true)
		private String ssn;
		
		@OneToMany(mappedBy="user")										//one user can have multiple orders (one-To-Many)
		private List<Order> orders;										//mappedBy="user": maps orders to the user field in the User entity.
		
	
		// No Argument Constructor
		public User() {
		}
	
		// Fields Constructor
		public User(Long userid, String username, String firstname, String lastname, String email, String role, String ssn) {
			this.userid = userid;
			this.username = username;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.role = role;
			this.ssn = ssn;
		}
	
		// Getters and Setters
		public Long getUserid() {
			return userid;
		}



		public void setUserid(Long userid) {
			this.userid = userid;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getFirstname() {
			return firstname;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getSsn() {
			return ssn;
		}

		public void setSsn(String ssn) {
			this.ssn = ssn;
		}

		public List<Order> getOrders() {
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}
		
		
		// To String
		@Override
		public String toString() {
			return "User [id=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
					+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
		}

		
		
	}