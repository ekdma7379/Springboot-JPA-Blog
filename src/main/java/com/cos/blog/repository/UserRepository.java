package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// Qustion : BEAN에 등록이 되나요?(스프링에 등록이 되고 DI가 되는가?)
// Answer : 자동으로 등록된다. 
public interface UserRepository extends JpaRepository<User, Integer> {
}
// JPA 이론
// JPA Naming 쿼리
// findByUsernameAndPassword => SELECT * FROM user WHERE username = ? AND password = ?;

// findByUsernameAndPassword 함수를 아래와 같이 만들 수도 있다.
//	@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);