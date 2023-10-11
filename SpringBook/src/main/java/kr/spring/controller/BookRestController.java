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

import org.springframework.web.bind.annotation.RestController;


import kr.spring.entity.BookVO;
import kr.spring.mapper.BookMapper;

@RestController
@RequestMapping("/board")
public class BookRestController {
	
	@Autowired
	private BookMapper mapper;
	
	// 도서 목록 로딩
	@GetMapping("/all")
	public List<BookVO> bookList() {
		System.out.println("도서 목록 보기");
		List<BookVO> list = mapper.getLists(); 
		
		return list;
	}
	
	// 도서 등록
	@PostMapping("/new")
	public void boardInsert(BookVO book) {
		System.out.println("게시글 작성 기능");
		mapper.bookInsert(book);
	}


	// 도서 삭제
	@DeleteMapping("/{num}")
	public void bookDelete(@PathVariable("num") int num) {
		System.out.println("도서 삭제");
		mapper.bookDelete(num);
	}
	
	
	// 보유 도서 수 수정
	@PutMapping("/update")
	public void bookUpdate(@RequestBody BookVO vo) {
		System.out.println("도서 수정");
		mapper.bookUpdate(vo);
	}
}
