package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.userService;

@RestController
public class UserApiController {
	
	@Autowired
	private userService userService;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		user.setRole(RoleType.USER);
		int result = userService.joinMember(user);
		System.out.println("가입 완료");
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	
	// 이건 스프링 전통적인 로그인 처리방법
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		System.out.println("UserApiController : login 호출됨");
//		User principal = userService.login(user); // principal : 접근 주체
//
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		} 
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
}
