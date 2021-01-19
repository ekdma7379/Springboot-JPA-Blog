package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
//@DynamicInsert insert시에 null인 값을 안넣어준다.
@Entity // User 클래스가 MySQL에 테이블이 자동으로 생성된다.
public class User {

	@Id // Primary key
	// IDENTITY : 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	// TODO : GenerationType의 다른 속성들을 공부해보자.
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int  id; // seq, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; // id
	
	@Column(nullable = false, length = 100) // 123456 => 해쉬 (나중에 해쉬로 변경하여 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// TODO : Enum으로 변경해보자. ( 제약조건을 걸 수 있다.) -> String에서 RoleType으로 변경함
	//@ColumnDefault("'user'") // 꼭 user 앞뒤에 " ' "를 붙여줘야 한다. -> 문자라고 알려주는 것
	// 하지만 columnDefault는 사용시 DB에서 Default 값으로 세팅해줘서 @DynamicInsert을 매번 붙혀줘야 한다. 그래서 사용 X
	@Enumerated(EnumType.STRING)
	private RoleType role; 
	
	// TODO : INPUT_DTIME 컬럼명 변경해보기
	@CreationTimestamp // 시간이 자동입력
	private Timestamp createDate;
	
}
