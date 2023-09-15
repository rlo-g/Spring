package kr.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// lombok 불러오기
@Data                // getter, setter
@AllArgsConstructor  // 전체 생성자
@NoArgsConstructor   // 기본 생성자
@ToString            // toString

public class Board {
	// BoardDTO
	
	private int idx;         // 번호
	private String title;    // 제목
	private String content;  // 내용
	private String writer;   // 작성자
	private String indate;   // 작성일
	private int count;       // 조회수
	
	// 생성자, getter, setter 생성해주는 api 사용(maven repository - lombok --> pom.xml에 추가 --> @data로 불러오기)


}
