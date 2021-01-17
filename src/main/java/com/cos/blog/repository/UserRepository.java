package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO
// Qustion : BEAN에 등록이 되나요?(스프링에 등록이 되고 DI가 되는가?)
// Answer : 자동으로 등록된다. 
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
