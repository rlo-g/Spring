package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.spring.entity.Board;
import kr.spring.mapper.BoardMapper;

// 앞으로 게시글 관련 요청을 할 때는 무조건!!!
//   --->  /board/하고싶은기능요청url
// ex) /board/boardList.do
//     /board/boardDelete.do     

// Rest 방식의 컨트롤러 import
@RestController
@RequestMapping("/board")  // 첫번째 통일성. 요청 url에 무조건 /board가 들어감
public class BoardRestController {

	// RestController
	// "비동기" 방식의 일만 처리하는 Controller
	// Rest 전송방식을 처리할 수 있다
	// - 요청 url + 전송방식(상태)을 묶어서 처리 가능 (똑같은 요청이지만 상태에 따라서 다르게 처리 가능)
	// 사용 이유 - url의 통일성 및 단순화
	// 요청을 하나만 하되, 상태에 따라서 다르게 응답
	
	@Autowired
	private BoardMapper mapper;   // MyBatis한테 JDBC(SQL문) 실행을 요청하는 객체

		
	// 게시글 목록 전체 불러오기
	@GetMapping("/all")
	// RequestMapping - get, post를 다 받아들임
	// get 방식만 받아들이고 싶음 (post는 받아들이지 않음) --> @GetMapping
	public List<Board> boardList() {
		// 동기 방식과 함께 사용 시, 반드시 public 앞에 "@ResponseBody" 붙여줘야 "비동기" 방식 가능
		System.out.println("게시글 전체보기 기능 수행");
		List<Board> list = mapper.getLists();  // 게시글 정보 받아오기 -> List (arraylist 큰 범위)
		
		return list;  // view name(.jsp)이 아니라 json 형태의 데이터를 바로 돌려줌 
	}
		
	// 글쓰기 
	@PostMapping("/new")
	// 게시글 작성 : post 방식 --> @PostMapping
	public void boardInsert(Board board) {
		// 반환해줄 데이터가 없으므로 void
		// 함수를 통해 세개의 데이터(title, content, writer)가 넘어오므로 이를 하나의 Board 타입에 담아줌
		System.out.println("게시글 작성 기능 수행");
		mapper.boardInsert(board);
	}
	
	
	// 게시글 상세보기
	@GetMapping("/{idx}")  // main에서 url에 idx 값을 같이 받아오므로 
	public Board boardContent(@PathVariable("idx") int idx) {
		// 하나의 게시글 정보를 가져오므로 반환 타입 BOARD
		// main에서 url에서 idx 값을 보냄
		//  --> @PathVariable
		Board vo = mapper.boardContent(idx);    // mapper가 idx에 해당되는 게시글 정보 vo를 불러와서 돌려줌
		return vo;
	}
	
	
	// 게시글 삭제
	@DeleteMapping("/{idx}")
	public void boardDelete(@PathVariable("idx") int idx) {
		//  --> @DeleteMapping
		// PathVariable 방식으로 보내줬으므로 --> @PathVariable("idx")
		System.out.println("게시글 삭제 기능 수행");
		mapper.boardDelete(idx);
	}
	
	// 해당되는 게시글에서 게시글 상세보기와 게시글 삭제기능은 url이 똑같음 
	//    --> 내부 삭제 기능을 통해서만 삭제할 수 있기 때문에 url 입력 시 상세보기가 나타남
	// get 방식은 게시글 보기를 해줌
	// 삭제 --> delete 방식으로 요청해야 삭제 가능 (외부에서 삭제 불가능. 보안 강력)
	
	
	
	// 게시글 수정
	@PutMapping("/update")
	public void boardUpdate(@RequestBody Board vo) {
		// 비동기-put 방식의 경우, json 문자열로 넘어오기 때문에 이를 vo로 넘겨주는 작업이 필요함 
		// ---> @RequestBody : 비동기 방식에서 vo로 묶을 때 사용
		System.out.println("게시글 수정 기능 수행");
		mapper.boardUpdate(vo);
	}
	
	// 조회수
	@PutMapping("/count/{idx}")  // main에서 url에 /count/ + idx 주소값을 보냄 
	public void boardCount(@PathVariable("idx") int idx) {
		System.out.println("조회수 기능 수행");
		mapper.boardCount(idx);
	}
}
