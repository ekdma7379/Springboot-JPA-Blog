package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
	
	// FetchType.EAGER : 무조건 데리고 온다.
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;
	
	//FetchType.LAZY : 필요할 때 가져온다. -> 댓글 펼치기 같은 기능이 있으면 사용 가능
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 는 연관관계의 주인이 아니다. 따라서 컬럼을 만들지 마세요. FK로 매핑되요.라는 어노테이션
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
