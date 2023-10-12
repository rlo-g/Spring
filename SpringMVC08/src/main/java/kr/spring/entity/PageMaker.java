package kr.spring.entity;

import lombok.Data;

@Data
public class PageMaker {
	// 실제 페이징 기법 처리하는 클래스
	
	// 페이징에 필요한 페이징 설정 정보(현재 페이지, 페이지 당 게시글 수, 현재 페이지 시작 번호)
	//  --> 가지고 있는 객체가 있어야 페이징 처리가 가능함
	private Criteria cri;
	
	private int totalCount;  // 총 게시글 수 --> 필요 페이지 수 알 수 있음
	
	private int startPage;  // 시작하는 페이지의 번호
	
	private int endPage;  // 끝페이지 번호
	
	private boolean prev;  // 이전버튼
	private boolean next;  // 다음버튼
	
	private int displayPageNum = 5;  // 하단에 보여지는 페이지의 개수(1 2 3 4 5 ...)
	
	
	// 총 게시글의 수를 구하는 메서드
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;  // 매개변수로 받은 totalCount를 내가 만든 객체에 넣어줌
		makePagein();  // 내가 가진 게시글의 수를 넣음
	}
	
	public void makePagein() {
		// 1. 화면에 보여질 마지막 페이지 번호
		//  - displayPageNum을 통해 하단에 보여줄 페이지의 수를 5개로 정함
		//     --> 현재 3페이지를 보고있다면, 우측 끝에 위치하는 마지막 페이지 번호는? : 5
		//      --> 현재 8페이지를 보고있다면, 우측 끝에 위치하는 마지막 페이지 번호는? : 10
		//       --> 3(현재 보고있는 페이지) / 5(몇개의 페이지를 볼것인지) : 소수 발생 시 올림 -> 1 * 10 -> 10
		//       --> 8/5 : 2*5 -> 10
		
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		// Math.ceil : 소수점 발생 시 알아서 자동으로 올려줌
		
		// 2. 화면에 보여질 시작페이지 번호
		//  현재 3페이지 -> 1~5(endPage) --> endPage - displayPageNum(보여줄 페이지 개수) + 1
		startPage = endPage - displayPageNum + 1;
		
		if(startPage <= 0) {
			// startPage가 0보다 작거나 같다면 1부터 시작할 수 있도록 조정
			startPage = 1;  
		}
		
		
		// 3. 최종페이지가 맞는지 유효성 검사
		//  ex) 실제로 글이 101개라면 20개 페이지 + 1개 페이지 필요
		//      --> 마지막 1개의 페이지 계산 필요
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));     // 중간 저장소 -- 소숫점 발생 시 반올림
		
		
		// 4. 화면에 보여질 마지막 페이지 유효성 체크
		// 현재 10페이지를 보고 있고 총 게시글의 수가 55개인 경우, endPage는 로 나오지만 실제 필요 페이지 수 11개
		if(tempEndPage < endPage) {
			// tempEndPage : 실제 마지막 번호(페이지) 알 수 있음 / endPage : 러프하게 잡아둔 예상 페이지 수
			// 실제 페이지 < 예상 페이지
			endPage = tempEndPage;  // 예상한 마지막 페이지가 실제 구현 페이지 숫자보다 높으면 치환
		}
		
		
		// 5. 이전/다음 페이지 버튼 존재여부 (삼항연산자)
		prev = (startPage == 1) ? false : true;
				// 조건식     --> true일 경우 실행 : false일 경우 실행
		next = (endPage < tempEndPage) ? true : false;
			// 현재 마지막 페이지 < 총 마지막 페이지 
		
		
		
	}
	
	
}
