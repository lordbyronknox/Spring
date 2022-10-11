package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private OrderRepository orderRepository;
	
	//get all orders for user
		@GetMapping("/{userid}/orders")							//getAllOrders() method, takes userid (from url) as arg.
		public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException
		{
			Optional<User> userOptional = userRepository.findById(userid);
			if(!userOptional.isPresent())
				throw new UserNotFoundException("User Not Found");
			return userOptional.get().getOrders();
		}
}
