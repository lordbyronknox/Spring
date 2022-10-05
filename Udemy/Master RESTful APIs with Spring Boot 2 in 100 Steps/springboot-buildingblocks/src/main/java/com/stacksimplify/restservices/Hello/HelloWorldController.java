package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//controller
@RestController
public class HelloWorldController {

	//simple GET method. 
	//URI/path: /helloworld
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")		//this line can be replaced with 
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Kalyan", "Reddy", "Hyderabad");
	}
	
}