package com.hoaxify.hoaxify.user;

import org.springframework.data.jpa.repository.JpaRepository;

//interface extends JpaRepository which provides database query methods.
//No annotation needed - spring looks automatically looks for interfaces that implement spring data interfaces.
public interface UserRepository extends JpaRepository<User, Long>{		//<type of entity, type of the Id field>

}
