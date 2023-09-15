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
import org.springframework.web.bind.annotation.ResponseBody;

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
		System.out.println("홈 기능 수행");
		return "main";
	}
	
	
//	// 게시글 목록 불러오기
//	@RequestMapping("/boardList.do")
//	public @ResponseBody List<Board> boardList() {
//		// 반드시 앞에 "@ResponseBody" 붙여줘야 "비동기" 방식 가능
//		System.out.println("게시글 전체보기 기능 수행");
//		List<Board> list = mapper.getLists();  // 게시글 정보 받아오기 -> List (arraylist 큰 범위)
//		
//		return list;  // view name(.jsp)이 아니라 json 형태의 데이터를 바로 돌려줌 
//	}
	

	
}
