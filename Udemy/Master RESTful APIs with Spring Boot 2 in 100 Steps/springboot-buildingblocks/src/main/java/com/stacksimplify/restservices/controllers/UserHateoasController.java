package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController 
{
//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
/*	
	
	//getUserById
		@GetMapping("/{id}")					//remove path (/users)
		public RepresentationModel<?> getUserById(@PathVariable("id") @Min(1) Long id){		//add @Min(1) constraint.
			
			try {
				Optional<User> userOptional = userService.getUserById(id);
				User user = userOptional.get();
				Long userid = user.getUserid();																//get id from user object.
				
				
//ControllerLinkBuilder depreciated - use WebMvcLinkBuilder				
//				Link selflink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel(); 		//link to this class.
				
				user.add(selflink);		//add the link to the user object.
				
//'Resources' depreciated - use 'CollectionModel' or 'EntityModel'.
//				Resource<User> finalResource = new Resource<User>(user);	
				CollectionModel<User> finalResource = (CollectionModel<User>) CollectionModel.of(user);
				
				return finalResource;
				
			}
			catch (UserNotFoundException ex) {
				//NOT_FOUND = 404.   ex.message = "User not found in user Repository" - defined in UserService.getUserById()
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage()); 
			}	
		}
		
		
		//getAllUsers Method
		@GetMapping
		public EntityModel<List<User>> getAllUsers() throws UserNotFoundException 
		{			
			List<User> allusers = userService.getAllUsers();		//get a list of users, assign to 'allusers'.
			
			for (User user : allusers)  			//create the 'self' link.
				{
				//get the user object
				Long userid = user.getUserid();
				//build the link using the WebMvcLinkBuilder, 
				//linkTo(this.class): link it to this class, 
				//slash(userid): add the userid to the URI
				//withSelfRel(): create the link with the self link relation.
				Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				user.add(selflink);
				
				//Relationship link with getAllOrders
				EntityModel<List<Order>> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
						.getAllOrders(userid);
				Link orderslink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
				user.add(orderslink);
				}
			
			
			EntityModel<List<User>> finalResource = EntityModel.of(allusers);
			return finalResource;
		}
	*/	
		
		
		
		// getUserById
		@GetMapping("/{id}")
		public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {

			try {
				Optional<User> userOptional =  userService.getUserById(id);
				User user = userOptional.get();
				Long userid = user.getUserid();
				Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				user.add(selflink);
				EntityModel<User> finalResource = EntityModel.of(user);
				return finalResource;
				
			} catch (UserNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}

		}
		
		// getAllUsers Method
		@GetMapping
		public CollectionModel<User> getAllUsers() throws UserNotFoundException {
			List<User> allusers = userService.getAllUsers();
			
			for(User user : allusers) {
				//Self Link 
				Long userid = user.getUserid();
				Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				user.add(selflink);
				
				//Relationship link with getAllOrders
				CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
						.getAllOrders(userid);
				Link orderslink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
				user.add(orderslink);
				
			}
			//Self link for getAllUsers
			Link selflinkgetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
			CollectionModel<User> finalResources = CollectionModel.of(allusers, selflinkgetAllUsers);
			return finalResources;

		}
		
		
}
