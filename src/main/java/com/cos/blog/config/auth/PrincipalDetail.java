package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails{
	private User user; // composition : 클래스 안에 포함하고 있는것

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	// 계정이 만료되지 않았는지 리턴한다.(true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	// 계정이 잠겨있지 않았는지 리턴한다.(true : 만료안됨)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	// 비밀번호가 만료되지 않았는지 리턴한다.(true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	// 계정활성화가 되어 있는건지 리턴한다.(true : 활성화)
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	// 계정이 가지고 있는 권한을 리턴한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		
		// 아래와 같은 람다식 표현이 가능한 것은 
		//GrantedAuthority에 getAuthority라는 함수가 하나밖에 없기 때문이다.
		// : 자바에서는 method를 인수로 받을 수 없기 때문에 Class로 감싼것
		
		collectors.add(()->{
			return "ROLE_"+user.getRole();
		});
		// 아래는 람다식 풀어놓은 버전
//		collectors.add(new GrantedAuthority() {
//		// ROLE을 받을 때 꼭 앞에 prefix로 "ROLE_"이 붙어야한다.
//		@Override
//		public String getAuthority() {
//			return "ROLE_"+user.getRole();
//		}
//	});
		return collectors;
	}
}
