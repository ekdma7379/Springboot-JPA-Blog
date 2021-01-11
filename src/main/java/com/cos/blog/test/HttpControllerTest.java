package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
// 사용자가 요청 -> 응답(HTML 파일) 하는 어노테이션
// @Controller
// 사용자가 요청 -> 응답(Data)
// browser 요청은 무조건 get요청만 된다.
@RestController
public class HttpControllerTest {
	@GetMapping("/http/get")
	public String getTest()
	{
		return "get 요청";
	}
	@PostMapping("/http/post")
	public String postTest()
	{
		return "postTest 요청";
	}
	@PutMapping("/http/put")
	public String putTest()
	{
		return "putTest 요청";
	}
	@DeleteMapping("/http/delete")
	public String deleteTest()
	{
		return "deleteTest 요청";
	}
}
