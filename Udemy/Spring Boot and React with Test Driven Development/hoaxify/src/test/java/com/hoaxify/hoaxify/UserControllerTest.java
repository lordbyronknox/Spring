package com.hoaxify.hoaxify;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;	//test runner

import com.hoaxify.hoaxify.shared.GenericResponse;
import com.hoaxify.hoaxify.user.User;
import com.hoaxify.hoaxify.user.UserRepository;

@RunWith(SpringRunner.class)
	//Initializes application for the test environment. Integration test (test the app as a whole, not just the class).
	// WebEnviroment.RANDOM_PORT - sets the port to run the web app on.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	//Spring lets you run the app in different profiles. Here we give it a custom profile 'test'.
@ActiveProfiles("test")
public class UserControllerTest {
	
	private static final String API_1_0_USERS = "/api/1.0/users";
	
	@Autowired							//dependency injection
	TestRestTemplate testRestTemplate;	//http client: to send the 'user' object to the backend.
	
	@Autowired							//inject the repository
	UserRepository userRepository;
	
	@Before								//tells jUnit to run this method before each test.
	public void cleanup() {				//method to clear the database before each test
		userRepository.deleteAll();
	}
	
	
//Declare the method as a 'test method'
	@Test
	public void postUser_whenUserIsValid_receiveOk()
	{
		User user = createValidUser();
		
//http client used to send POST request. ("post URL", object to send, response type)
//Post response assigned to variable 'response'
		ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
		
//check that the response is 'HttpStatus.OK' (this is called 'assertion')
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	
//test method to test 'createValidUser' method.
	@Test
	public void postUser_whenUserIsValid_userSavedToDatabase() {
		User user = createValidUser();
		testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
		assertThat(userRepository.count()).isEqualTo(1);
		
	}
	
//test method to return success message when user is valid
	@Test
	public void postUser_whenUserIsValid_receiveSuccessMessage()
	{
		User user = createValidUser();		
		//http client used to send POST request. ("post URL", object to send, response type)
		//Post response assigned to variable 'response'
		ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);		
		//check that the response is 'HttpStatus.OK' (this is called 'assertion')
		assertThat(response.getBody().getMessage()).isNotNull();
	}
	
//test to see if user's password is hashed in the database
	@Test
	public void postUser_whenUserIsValid_passwordIsHashedInDatabase() {
		User user = createValidUser();
		testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
		List<User> users = userRepository.findAll();	//return all users in the database as a List
		User inDB = users.get(0);						//get the 1st (at moment... the only) user in the list.
		//test to see if the user's password is NOT the same once stored in the DB. (It should fail if not hashed)
		assertThat(inDB.getPassword()).isNotEqualTo(user.getPassword());
	}
	
	
	//method to create a user
	private User createValidUser() {
			User user = new User();
			user.setUsername("test-user");
			user.setDisplayName("test-display");
			user.setPassword("P4ssword");
			return user;
		}

}
