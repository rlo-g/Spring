package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.entity.Board;
import kr.spring.entity.Criteria;
import kr.spring.entity.PageMaker;
import kr.spring.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	// BoardService -> interface
	// 실제로 구현한 클래스 -> BoardServiceImpl
	//   --> BoardServiceImpl가 BoardService로 업캐스팅 되므로 부모타입인 BoardService로 받아도 됨!
	
	
	@GetMapping("/list")
	public String boardList(Model model, Criteria cri) {
		// boardList.do에서 요청이 들어오면 board/list를 보여줌
		// 페이징에 대한 정보를 가지고 있는 Criteria(기준점) 
		//  --> 이제는 페이지 정보를 알고 있는 Criteria 객체를 Service에게 전달
		
		List<Board> list = service.getList(cri); // criteria를 기준으로 list(게시글)를 가져옴
		// getList()  -  BoardService의 추상메서드인데 사용할 수 있는 이유? 
		//  - 자식 타입이 업캐스팅된 것. 자식에서 기능을 구현 시켜놓음 --> 자식 타입의 getList()가 나옴
		
		// 페이징 처리에 필요한 PageMaker 객체도 생성해야 함 (페이지 계산기)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);   // pageMaker가 페이징 기법을 사용할 수 있도록 cri 객체 전달
		pageMaker.setTotalCount(service.totalCount());  // 페이징 기법을 사용하려면 전체 게시글 수를 알아야 함 --> service를 통해서 totalCount를 가져와서 절달
		
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);  //페이징 정보를 알고있는 객체 전달
		return "board/list";
	}
	
	
	@GetMapping("register") // 페이지 이동만 하는 get 방식의 register이므로 GetMapping
	public String register() {
		return "board/register";
	}
	
	
	@PostMapping("/register")
	public String register(Board vo, RedirectAttributes rttr) {
		// RedirectAttributes - 모달창을 띄우기 위해서 사용

		// service에게 vo를 주면서 register 기능 요청
		service.register(vo);
		rttr.addFlashAttribute("result", vo.getIdx());  // 게시글 작성 시, 게시글의 고유번호를 result에 넣어줌
		
//		System.out.println(vo.toString());  --> register 수행 시 insertSelectKey가 실행되므로 register 실행 전에 수행한 syso와 다르게 idx, boardGroup 값이 달라짐 
		
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/get")											// model.addAttribute(cri)와 같은 역할 - cri 정보를 가지고 이동
	public String get(@RequestParam("idx") int idx, Model model, @ModelAttribute("cri") Criteria cri) {
		
		Board vo = service.get(idx); 	// 해당되는 idx의 게시글을 가져옴	
		model.addAttribute("vo", vo);  // get()을 통해 가져온 해당 idx의 board 정보를 Model에 담아 다른 jsp에 보여줌
		
		return "board/get";
	}
	
	
	@GetMapping("/modify")
	public String modify(@RequestParam("idx") int idx, Model model) {
		// get 방식을 통해 보내준 idx를 int idx로 받는다
		
		Board vo = service.get(idx);   // 해당되는 idx의 게시글을 가져옴
		model.addAttribute("vo", vo);  // vo를 model에 담아서 수정 페이지로 보내줌
		return "board/modify";  // 게시글 수정 페이지로 이동
	}
	
	@PostMapping("/modify")
	public String modify(Board vo) {
		// 중복 정의 : 오버로딩
		
		service.modify(vo);
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/remove")
	public String remove(@RequestParam("idx") int idx) {
		service.remove(idx);

		return "redirect:/board/list";
	}
	
	@GetMapping("/reply")
	public String reply(@RequestParam("idx") int idx, Model model) {
		Board vo = service.get(idx);  // 댓글 달고 싶은 게시글의 정보 가져오기
		model.addAttribute("vo", vo);  // reply.jsp로 보내주기 위해서 model에 담기
		return "board/reply";  // reply.jsp로 이동
	}
	
	@PostMapping("/reply")
	public String reply(Board vo) {
		// 부모(원본)글 번호, 작성id, 제목, 답글, 작성자 이름을 받아옴
		service.reply(vo);
		return "redirect:/board/list";
	}
}
