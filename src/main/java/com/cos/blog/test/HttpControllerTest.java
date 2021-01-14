package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// 사용자가 요청 -> 응답(HTML 파일) 하는 어노테이션
// @Controller
// 사용자가 요청 -> 응답(Data)
// browser 요청은 무조건 get요청만 된다.
@RestController
public class HttpControllerTest {
	@GetMapping("/http/lombok")
	public String lombokTest() {
		// builder 패턴을 쓰면 생성자 순서를 안 지켜도 된다.
		Member m = Member.builder().id(1).email("test@naver.com").build();
		//Member m = new Member(1,"ssar","1234","test");
		m.setUsername(null);
		System.out.println(String.join(",", String.valueOf(m.getId()),m.getUsername(), m.getPassword(),m.getEmail()));
		return "<h1>Hello</h1>";
	}
	
	@GetMapping("/http/get")
	public String getTest (Member m)
	{
		m.setId(100);
		return String.join(",", String.valueOf(m.getId()),m.getUsername(), m.getPassword(),m.getEmail());
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) //String text : text/plain, RequestBody -> Member m 해주는 친구가 Springboot의 MessageConverter
	{
		return "Post요청 : "+String.join(",", String.valueOf(m.getId()),m.getUsername(), m.getPassword(),m.getEmail());
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m)
	{
		return "putTest 요청"+String.join(",", String.valueOf(m.getId()),m.getUsername(), m.getPassword(),m.getEmail());
	}
	@DeleteMapping("/http/delete")
	public String deleteTest()
	{
		return "deleteTest 요청";
	}
}
