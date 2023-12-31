package kr.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.entity.Board;
import kr.spring.mapper.BoardMapper;
import lombok.NoArgsConstructor;

// 핸들러 맵핑이 보트 컨트롤러를 찾을 수 있게 함
// 현재 클래스를 랜들러 맵핑이 찾기 위해 컨트롤러로 등록 (반드시 처음 만들었던 패키지 kr.spring.cotroller에 있어야 함)
// @ : 어노테이션
@Controller


public class BoardController {
	// POJO
		
								  // spring에 boardMapper 객체 생성된 것을 자동으로 가져옴 ( = new Board~ )
								  // SqlSessionFactoryBean를 가져다 씀 (SqlSessionFactoryBean에서 boardMapper 기능을 구현했으므로)
								  // SqlSessionFactoryBean의 부모는 boardMapper
								  // SqlSessionFactoryBean는 자식클래스이므로 @Autowired로 가져올 때 업캐스팅됨
	@Autowired
	private BoardMapper mapper;   // MyBatis한테 JDBC(SQL문) 실행을 요청하는 객체
	
	
	@RequestMapping("/")    // 요청 url("/")로 들어왔을 때 아래 기능을 수행하겠다
	public String home() {
		// "/"에 요청이 들어왔을 때 view name을 돌려주는 것이 아니라 "/boardList.do"로 이동할 수 있도록 전달 (redirect 방식)	
		return "redirect:/boardList.do";
	}
	
	
	@RequestMapping("/boardList.do")  // 요청 url("/boardList.do")로 들어왔을 때 아래 기능을 수행하겠다
	public String boardList(Model model) {
		System.out.println("게시판 목록 보기 기능 수행");
		// 게시글 정보 가져오기
		// 한 개의 게시글 - 번호, 제목, 내용, 작성자, 작성일, 조회수 
		// 스프링에서 데이터 넘기려면 무조건 dto 형태로 --> Board
		
/**		Board b1 = new Board(1, "배고파요", "밥뭐먹지", "햄미", "2023-09-05", 11);
//		Board b2 = new Board(2, "ㄴㄷ", "cu", "멈뭄미", "2023-09-05", 2);
//		Board b3 = new Board(3, "오늘계획", "sqld 2단원 공부하기", "애옹", "2023-09-05", 22);
//		Board b4 = new Board(4, "집에 언제 가지?", "집가고싶다", "햄미", "2023-09-05", 143);
//		Board b5 = new Board(5, "주말!!!!", "언제옴 ㅡㅡ", "멈뭄미", "2023-09-05", 11);
//
//		// 하나의 보드 객체는 하나의 게시글을 의미함 -> 지금 다섯개
//		// 세션에 하나씩 일일이 다 담지말고 가변배열인 ArrayList에 담기
//		ArrayList<Board> list = new ArrayList<Board>();
//		list.add(b1);
//		list.add(b2);
//		list.add(b3);
//		list.add(b4);
//		list.add(b5); **/
		
		// 전체 게시글 조회 기능
		// 하나의 게시글은 번호, 제목, 내용, 작성자, 작성일을 가지고 있음 ==> DTO인 Board 객체로 받아옴 
		//   ==> 여러개의 게시글 list 형태 (ArrayList는 List의 일부)
		List<Board> list = mapper.getLists();
		
		
		// list에 저장된 배열을 boardList.jsp로 옮기기
		// 로그인의 경우, 어디에 가든지 확인할 수 있도록 session에 저장
		// session은 브라우저를 끄지 않는 한 계속 살아 있음
		// 게시글 정보는 하나의 페이지에서만 확인하므로 session에 저장할 필요가 없음
		//  --> model을 이용하여, model에 잠깐 저장해둠
		//  --> 매개변수로 [Model 변수명] 입력
		model.addAttribute("list", list);  // list라는 이름으로 list 가변배열 담아주기
		
		// 객체 바인딩(동적 바인딩)
		//  - 요청에 따라 특정 영역 안에 데이터를 넣고 이동하는 것
		// 리턴한 값이 이동할 수 있도록 경로를 만들어 주는 것 -->  view resolver(servlet-context.xml)
		return "boardList";   // WEB-INF/views/boardList.jsp -> forward 방식으로 이동
	}
	// server를 restart하면 console에 RequestMappingHandlerMapping이 requestMapping에 요청한 값을 이해한 것을 알 수 있음
	
	
	@RequestMapping("/boardForm.do")
	public String boardForm() {
		System.out.println("글쓰기 페이지 이동");
		return "boardForm";
	}
	
	
	@RequestMapping("/boardInsert.do")
	public String boardInsert(Board board) {
		// 매개변수로 Board board 작성 시
		// 1. input 태그의 name과 vo(Board.java)의 필드명이 일치해야 함
		// 2. Board.java에 기본생성자가 있어야 함 (@NoArgsConstructor)
		// 3. vo에 getter, setter 존재
		
		System.out.println("게시글 등록 기능 수행");		
		// System.out.println(board.toString()); --> 제대로 넘어오는지 확인
		
		mapper.boardInsert(board);
		
		return "redirect:/boardList.do";
	}
	
	@RequestMapping("/boardContent.do/{idx}")
	public String boardContent(@PathVariable("idx") int idx, Model model) {
		System.out.println("게시글 상세보기 기능 수행");
		Board vo = mapper.boardContent(idx);  // idx는 고유번호이므로 하나의 게시글을 가져옴 -> 하나의 Board 객체는 하나의 게시글을 의미한다
		//System.out.println(vo.toString());
		
		// 모델에 담아서 이동
		model.addAttribute("vo",vo);
		
		// (+) 게시글 조회수 증가
		mapper.boardCount(idx);  // count = count + 1
		
		return "boardContent";
	}
	
	
	@RequestMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		System.out.println("게시글 삭제 기능 수행");
		
		mapper.boardDelete(idx);
		
		return "redirect:/boardList.do";
	}
	
	
	@RequestMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		// 게시글 수정 버튼을 누르면, 수정 페이지로 이동해야함 => return은 boardUpdateForm.jsp
		// 네임몇과 변수명이 같으면 @RequestParam("idx") 안 써도 됨 (쓰면 변수명 다르게 할 수 있음)
		System.out.println("게시글 수정 페이지 이동 기능 수행");
		
		Board vo = mapper.boardContent(idx);  // idx를 기준으로 하나의 게시글을 가져와서 vo에 담아줌
		
		// 데이터를 스프링에 잠깐동안 저장해줌 (모델)
		model.addAttribute("vo", vo);		
		
		return "boardUpdateForm";
	}
	
	@RequestMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		// idx, title, content, writer가 넘어옴 --> Board vo에 담아서 sql문으로 넘기기
		System.out.println("게시글 수정 기능 수행");
		
		mapper.boardUpdate(vo);
		
		return "redirect:/boardList.do";
	}
	
}
