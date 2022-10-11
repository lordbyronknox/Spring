package com.stacksimplify.restservices.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

@Entity												//@Entity, as this class creates an entity (table).
@Table(name="orders")
public class Order extends RepresentationModel<Order>{	//add 'extends RepresentationModel'
	
	@Id												//set as primary key
	@GeneratedValue									//auto-generate IDs
	private Long orderid;
	private String orderdescription;
	
	@ManyToOne(fetch = FetchType.LAZY)				//Many orders can be associated with one user.
	@JsonIgnore										//Ignore this field during serialization and deserialization (persist the object to memory)
	private User user;								//FetchType.LAZY: This does not load the relationships unless you invoke it via the getter method.

//constructor using super class
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	
//getters and setters
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public String getOrderdescription() {
		return orderdescription;
	}

	public void setOrderdescription(String orderdescription) {
		this.orderdescription = orderdescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
