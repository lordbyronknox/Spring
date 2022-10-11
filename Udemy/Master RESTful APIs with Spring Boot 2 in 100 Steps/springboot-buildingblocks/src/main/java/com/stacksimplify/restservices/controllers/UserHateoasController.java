package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.hateoas.RepresentationModel;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	//getUserById
		@GetMapping("/{id}")					//remove path (/users)
		public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id){		//add @Min(1) constraint.
			
			try {
				Optional<User> userOptional = userService.getUserById(id);
				User user = userOptional.get();
				Long userid = user.getId();																//get id from user object.
				
				
/*ControllerLinkBuilder depreciated - use WebMvcLinkBuilder				
				Link selflink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel(); */
				Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel(); 		//link to this class.
				
				user.add(selflink);		//add the link to the user object.
				
/*'Resources' is now 'CollectionModel'
				Resource<User> finalResource = new Resource<User>(user);	*/
				EntityModel<User> finalResource = EntityModel.of(user);
				//List<Order> finalResource = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
				
				return finalResource;
				
			}
			catch (UserNotFoundException ex) {
				//NOT_FOUND = 404.   ex.message = "User not found in user Repository" - defined in UserService.getUserById()
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage()); 
			}	
		}
		
		
		//getAllUsers Method
		@GetMapping
		public List<User> getAllUsers() 
		{			
			return userService.getAllUsers();			
		}
		
		
}
