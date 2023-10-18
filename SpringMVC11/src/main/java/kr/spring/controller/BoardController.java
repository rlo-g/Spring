package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.entity.Board;
import kr.spring.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		// 게시글 전체 조회
		// controller -> service -> repository 요청
		List<Board> list = boardService.getList();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	@PostMapping("register")
	public String register(Board vo) {
		boardService.register(vo);
		return "redirect:/list";
	}
	
	@GetMapping("/get")
	public @ResponseBody Board get(@RequestParam("idx") Long idx) {
		// 일반 컨트롤러에 비동기 방식을 받아온다면 @ResponseBody
		Board vo = boardService.get(idx);  // boardService에게 매개변수와 일치하는 idx를 가지는 게시글 돌려주라고 요청
		return vo;
	}
	
	@GetMapping("remove")
	public String remove(@RequestParam("idx") Long idx) {
		boardService.delete(idx);
		return "redirect:/list";
	}
	
	@PostMapping("/modify")  // register 태그 변경해서 사용 (register : post 방식)
	public String modify(Board vo) {
		boardService.update(vo);
		return "redirect:/list";
	}
}
