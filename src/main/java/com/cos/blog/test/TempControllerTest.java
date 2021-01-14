package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로 : src/main/resource/static
		// 따라서 리턴명 : /home.html
		// 풀경로 : src/main/resource/static/home.html
		return "/home.html";
	}
	@GetMapping("/temp/image")
	public String tempImg() {
		return "/a.png";
	}
	@GetMapping("/temp/jsp")
	public String tempjsp() {
		return "test";
	}
}
