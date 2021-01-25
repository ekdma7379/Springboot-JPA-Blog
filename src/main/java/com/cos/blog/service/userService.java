package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


// @Service어노테이션 필요 이유 :  스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IoC를 해준다.
@Service
public class userService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public Integer joinMember(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("userService : joinMember() "+ e.getMessage());
		}
		return -1;
	}
	@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에까지 트랜잭션 종료(정합성 유지)
	public User login(User user) {
		try {
			return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("userService : login() "+ e.getMessage());
		}
		return null;
	}
}
