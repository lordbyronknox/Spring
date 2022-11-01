package com.hoaxify.hoaxify;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.hoaxify.hoaxify.user.User;

@RunWith(SpringRunner.class)
	//Initializes application for the test environment. Integration test (test the app as a whole, not just the class).
	// WebEnviroment.RANDOM_PORT - sets the port to run the web app on.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	//Spring lets you run the app in different profiles. Here we give it a custom profile 'test'.
@ActiveProfiles("test")

public class UserControllerTest {
	
	@Autowired						//dependency injection
	TestRestTemplate testRestTemplate;	//http client: to send the 'user' object to the backend.
	
	//Declare the method as a 'test method'
	@Test
	public void postUser_whenUserIsValid_receiveOk()
	{
		User user = new User();
		user.setUsername("test-user");
		user.setDisplayName("test-display");
		user.setPassword("P4ssword");
		
		//http client used to send POST request. ("post URL", object to send, response type)
		//Post response assigned to variable 'response'
		ResponseEntity<Object> response = testRestTemplate.postForEntity("/api/1.0/users", user, Object.class);
		
		//check that the response is 'HttpStatus.OK' (this is called 'assertion')
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
