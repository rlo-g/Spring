package kr.spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity  // Board VO가 Database Table로 만들 때 설정
@Data
public class Board {  // VO <--- ORM ---> Table
	
	@Id  // PK를 의미
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 1씩 증가 (== AutoIncrement)
	private Long idx;  // Long : 정수 표현 방식 중 가장 긴 숫자 표현 -> 호환을 위해 Long형 사용 / idx(게시글 고유 번호)
	
	private String title;
	
	@Column(length = 2000)  // 길이 지정 -> 기본 길이 255  
	private String content;
	
	@Column(updatable = false)  // 수정 시 작성자는 변경하지 않겠다
	private String writer;
	
	// 게시물 작성 시 날짜 직접 입력x, 수정불가, 초기값 datetime 형태 default로 현재 시간 입력
	@Column(insertable = false, updatable = false, columnDefinition = "datetime default now()")  
	private Date indate;
	
	@Column(insertable = false, updatable = false, columnDefinition = "int default 0")
	private Long count;
	
	

}
