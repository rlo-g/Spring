package kr.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping("/boardList.do")
	public String boardList() {
		// boardList.do에서 요청이 들어오면 board/boardList를 보여줌
		
		return "board/boardList";
	}
}
