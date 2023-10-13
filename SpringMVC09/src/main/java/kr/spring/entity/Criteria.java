package kr.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@ToString
public class Criteria {
	// Criteria : 기준
	// 페이징 옵션 설정
	
	// 검색 기능에 필요한 변수
	private String type;  // 이름, 제목, 내용 중 무엇인지 선택하는 필터
	private String keyword;  // 검색 내용
	
	
	private int page;   // 현재 페이지 번호를 저장할 변수 (ex. 현재 페이지 3page)
	private int perPageNum;   // 한 페이지에 보여줄 게시글의 수 (페이지 당 보여줄 게시글의 수)
	
	// Criteria 기본 세팅 생성자를 통해서 하기
	public Criteria() {
		this.page = 1;
		this.perPageNum = 5;
	}
	
	// 현재 페이지 게시글의 "시작번호를 구하는" 메소드
	//   ex) 100의 게시글을 페이지 당 10개의 게시글씩 보여줄 것 : 1page -> 1~10, 2p -> 11~20
	//      - 예외 : mysql에서는 시작값을 0으로 인식하므로 1page -> 0~9, 2p -> 10~19, 3p -> 20~29
	public int getPageStart() {
		
		return (page-1)*perPageNum;  // 한 페이지의 게시글이 return 값부터 시작 (현재 3page, 페이지 당 10개씩 --> 3page에서 20부터 시작)
	}
}
