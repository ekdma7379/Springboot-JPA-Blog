package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// RestController : 데이터 반환
@RestController
public class DummyControllerTest {
	// 의존성 주입 (DI)
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
// 1) page한 부분 리턴	
//	// 한 페이지당 2건에 데이터를 리턴받아 볼 예정
//	// http://localhost:8000/blog/dummy/user/page?page=0
//	// 위처럼 요청하면 첫번째 페이지 조회
//	@GetMapping("/dummy/user")
//	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
//		Page<User> users = userRepository.findAll(pageable);
//		return users;
//	}
	

		// 위처럼 조회해온 페이지객체의 content(실질적인 내용)부분만 사용
		@GetMapping("/dummy/user")
		public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
			Page<User> pagingUser = userRepository.findAll(pageable);
//			if (pagingUser.isLast()) {
//				// TODO : 만약 마지막 페이지라면 뭔가를 하여라
//			}
//			if (pagingUser.isFirst()) {
//				// TODO : 만약 첫 페이지라면 뭔가를 하여라
//			}
			List<User> users = pagingUser.getContent();
			return users;
		}
	
	// {id}주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// 만약 user id가 4라는 사람을 찾아오는데 해당 id를 가지고있는 사람이 없다면 프로그램 에러이기 때문에 optional 하게 받을 수 있다.
		// get : 해당 데이터는 무조건 가져온다.없을리 없다. 에러 생각 X
//		User user = userRepository.findById(id).get();
		// orElseGet : 찾으면 해당 객체 반환 아니면 초기화 후 해당 객체 반환
//		 User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				// TODO Auto-generated method stub
//				return new User();
//			}
//		});
		
//		// 람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자가 없습니다.");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바오브젝트
		// 변환 ( 웹브라우저가 이해할 수 있는 데이터 ) -> json (레거시 스프링이라면 예를들어 Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약 자바오브젝트를 웹브라우저에 리턴하게 되면 MessageConverter가 Jackson 라이브러리 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에 던져줍니다.
		return user;
	}
	
	// postman에서 x-www-form-urlencoded는 key=value&key=value 형식으로 전송됨
	// key=value(약속된 규칙임) 이렇게 보내주면 JPA가 User처럼 Object를 받게 해줌
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("Id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("email : "+user.getCreateDate());
		System.out.println("Role : "+user.getRole());
		System.out.println("class : "+user.getClass());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
			return "삭제되었습니다. "+ id;
		} catch (Exception e) {
			return "삭제 실패하였습니다. "+ id;
		}
		
	}
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		// 방법 1.
		// save 함수는 id를 전달하지 않으면 insert 한다.
		// save 함수는 id를 전달하면 id에 대한 데이터가 있으면 update/ 없으면 insert를 한다.
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setPassword(requestUser.getEmail());
		// id가 1인 친구를 저장하려고 하는데 있으면 해당 raw를 update하고 없으면 새로 insert한다.
		
		// 더티 체킹
		// @Transactional이 있으면 아래와 같은 save 함수를 호출하지 않아도 괜찮다.
		//userRepository.save(user);

		return user;
	}
}
