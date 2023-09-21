package kr.spring.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.spring.entity.Member;
import kr.spring.mapper.MemberMapper;

@Controller
public class MemberController {
	
	// ** mapper 가져오기 **
	//  - 구현해주는 factorySession을 가져오기 위해서 @Autowired
	@Autowired
	private MemberMapper mapper;
	
	
	// 회원가입 클릭 시 회원가입 페이지 이동
	@RequestMapping("/joinForm.do")
	public String joinForm() {
		// 컨트롤러에서 실행 시(return) views에서 시작
		return "member/joinForm";
	}
	
	@RequestMapping("/idCheck.do")
	public @ResponseBody int idCheck(@RequestParam("memId") String memId) {
		// restController가 아닌데 비동기 방식을 사용하고 싶다면 반드시 @ResponseBody 작성
		// 매개변수 memID 받아오기
		
		Member m = mapper.idCheck(memId);
		// m == null --> 아이디 사용 가능
		// m != null --> 아이디 사용 불가능 (중복)
		if(m != null || memId.equals("")) {
			return 0;
			
		}else {
			return 1;
		}
	}
	
	
	@RequestMapping("/join.do")
	public String join(Member vo, RedirectAttributes rttr, HttpSession session) {
		// vo에 회원가입 시 받은 정보들이 들어있음(id, pw, nick, name, age, gender, email)
		System.out.println("회원가입 기능 요청");
		
		// 유효성 검사 (1차)
		if(vo.getMemId() == null || vo.getMemId().equals("") ||
				vo.getMemPw() == null || vo.getMemPw().equals("") ||
				vo.getMemName() == null || vo.getMemName().equals("") ||
				vo.getMemAge() == 0 ||
				vo.getMemEmail() == null || vo.getMemEmail().equals("")) {
			// 하나의 매개변수라도 이름이 다르거나 값이 비어있는 경우
			// 회원가입을 할 수 없다
			
			// 실패 시, joinForm.do로 msgType과 msg의 내용을 보내줌
			// msgType : 실패메세지,  msg : "모든 내용을 입력하세요"
			//   - model 유지는 forwarding 방식만 가능하므로 model에 저장해서 보낼 수 없음
			//   - < RedirectAttributes >
			//      - redirect 방식으로 이동할 때 보낼 데이터를 저장하는 객체 (=모델, 일회성)  
			
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			return "redirect:/joinForm.do";
			
		}else {
			// 회원가입을 시도할 수 있는 부분
			
			vo.setMemProfile("");   // 프로필 값 null로 지정해두기
			int cnt = mapper.join(vo);  // 회원가입 성공 여부 기록 변수 cnt
			
			if(cnt == 1) {
				System.out.println("회원가입 성공!");
				rttr.addFlashAttribute("msgType", "성공 메세지");
				rttr.addFlashAttribute("msg", "회원가입에 성공했습니다!");		
				
				// 회원가입 성공 시 로그인 기능까지 실행하기
				//  - 회원가입에 대한 정보를 session에 넣어줌
				session.setAttribute("mvo", vo);
				
				// 회원가입 후, index.jsp ("/")로 이동
				return "redirect:/";
				
			}else {
				System.out.println("회원가입 실패...");
				rttr.addFlashAttribute("msgType", "실패 메세지");
				rttr.addFlashAttribute("msg", "회원가입에 실패했습니다.");				
				return "redirect:joinForm.do";			
			}
		}	
	}
	
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		
		// sisson에 들어있는 로그 정보 없애기
		session.invalidate();
		
		return "redirect:/";
	}

	
	@RequestMapping("/loginForm.do")
	public String loginForm() {	
		// 로그인 버튼 클릭 시, 로그인 페이지로 이동
		return "member/loginForm";   // loginForm.jsp
	}
	
	
	@RequestMapping("/login.do")
	public String login(Member vo, RedirectAttributes rttr, HttpSession session) {
		
		// 문제
		// mapper에 login이라는 메서드 이름으로 로그인 기능 수행하기
		// 단, 로그인 성공 시 index.jsp 이동 후 "로그인에 성공했습니다" 모달창 띄우기
		// 로그인 실패 시 login.jsp 이동 후 "로그인에 실패했습니다" 모달창 띄우기
		
		Member mvo = mapper.login(vo);   // 로그인 후 해당 회원의 정보를 모두 받아와야 하므로 Member 객체에 저장
		
		if(mvo != null) {
			// 로그인 성공
			System.out.println("로그인 성공!");
			
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "로그인에 성공했습니다!");		
			
			session.setAttribute("mvo", mvo);  // session에 로그인 정보 저장	
			return "redirect:/";
			
		}else {
			// mvo == null : 로그인 실패
			System.out.println("로그인 실패...");
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "로그인에 실패했습니다.");				
			return "redirect:loginForm.do";			
		}
	}
	
	
	@RequestMapping("/updateForm.do")
	public String updateForm() {
		return "member/updateForm";
	}
	
	
	
	@RequestMapping("/update.do")
	public String update(Member member, RedirectAttributes rttr, HttpSession session) {
		System.out.println(member.toString());
				
		// 문제
		// mapper의 update 메서드를 통해 해당 회원의 정보 수정하기
		
		// 조건 1. 하나라도 입력하지 않은 데이터가 있으면 updateForm.jsp로 다시 돌려보내면서 "모든 내용을 입력하세요"모달창 띄우기
		if(member.getMemPw() == null || member.getMemPw().equals("") ||
				member.getMemName() == null || member.getMemName().equals("") ||
				member.getMemAge() == 0 ||
				member.getMemEmail() == null || member.getMemEmail().equals("")) {
		
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			return "redirect:/updateForm.do";
			
		}else {
		  // 유효성 검사 성공
			
			// member.setMemProfile("");  --> 기존에 가지고 있는 프로필 이미지를 가져와야함
			
//			1. 로그인한 회원의 프로필 가져오기
//			Member mvo = (Member)session.getAttribute("mvo");
//			member.setMemProfile(mvo.getMemProfile());
			
			
			int cnt = mapper.update(member);
					
			if(cnt != 1) {
				// 조건 2. 회원수정 실패 시 updateForm.jsp로 다시 돌려 보내면서 "회원 정보 수정이 실패했습니다" 모달창 띄우기
				System.out.println("회원수정 실패");
				rttr.addFlashAttribute("msgType", "실패 메세지");
				rttr.addFlashAttribute("msg", "회원정보 수정에 실패했습니다.");				
				return "redirect:/updateForm.do";			
				
			}else {
				// 조건 3. 회원수정 성공 시 index.jsp로 보내고 "회원정보 수정에 성공했습니다" 모달창 띄우기
				System.out.println("회원수정 성공");
				rttr.addFlashAttribute("msgType", "성공 메세지");
				rttr.addFlashAttribute("msg", "회원정보 수정에 성공했습니다.");				
				
				// 로그인한 정보는 session에서 유지되고 있음 -> 회원정보 수정 시 session도 업데이트 해줘야함
				session.setAttribute("mvo", member);	

				return "redirect:/";		
		} }
	}
	
	
	
	@RequestMapping("/imageForm.do")
	public String imageForm() {
		
		return "member/imageForm";    // imageForm.jsp로 이동
	}
	
	
	@RequestMapping("/imageUpdate.do")
	public String imageUpdate(HttpServletRequest request, HttpSession session, RedirectAttributes rttr) {
		
		// 파일 업로드 객체는 내부적으로 만들어져있지 않아서 직접 생성해야함
		// 파일 업로드를 할 수 있게 도와주는 객체 MultipartRequest(cos.jar)를 생성하기 위해서는 다섯개의 정보 필요
		//   - 요청 데이터, 저장 경로, 최대 크기, 인코딩, 파일명 중복제거
		MultipartRequest multi = null;
		
		// 1) 요청데이터 - 내가 올린 데이터를 저장하고 있는 객체 (request) -->  HttpServletRequest request		
		
		// 2) 저장경로 - 회원 별 업로드 이미지는 모두 webapp-resources-upload에 저장됨	
		String savePath = request.getRealPath("resources/upload");
		
		// 3) 이미지 최대 크기
		int fileMaxSize = 10 * 1024 * 1024;
		
		
		
		// A. 기존 프로필 이미지 삭제
		//  - 로그인 한 회원의 프로필 값을 가져와야함	
		String memId = ((Member)session.getAttribute("mvo")).getMemId();
	
		// getMember 메서드는 memId와 일치하는 회원의 Member 정보를 가져온다
		String oldImg = mapper.getMember(memId).getMemProfile();    // 기존 프로필 이미지
		
		
		// B. 기존 프로필 사진 삭제
		//  - 파일 객체 생성
		//   - savaPath 안에 저장되어 있는 oldImg를 가져옴 (모든 이미지 파일은 savePath에 저장되어 있음)
		File oldFile = new File(savePath+"/"+oldImg);
		if(oldFile.exists()) {
			// oldFile이 존재할 때만 이미지를 삭제
			oldFile.delete();
		}
		
		
		
		try {
			// 1)~3)과 인코딩, 파일명 중복제거를 multi 객체 안에 넣어준다
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {  												// 중복된 이름이 올라왔을 때 뒤에 숫자를 붙여줌
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// C. 업로드 파일 확장자 제한하기
		//  - 내가 업로드한 파일 가져오기
		File file = multi.getFile("memProfile");
		
		if(file != null) {
			// 업로드가 된 상태
			// System.out.println(file.getName());
			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);  	
											// substing - 특정 문자열을 기준으로 자름
											// 파일 이름의 마지막 .(확장자 시작점)을 찾아서 그것을 기준으로 그 뒤의 문자열을 가져오겠다
			
			ext = ext.toUpperCase();  // 소문자(png) --> 대문자(PNG)
			
			if(!(ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPG"))) {
				// (!) 확장자가 png, gif, jpg가 아니라면
				
				if(file.exists()) {
					// 등록된 파일이 있을 때, 파일 삭제
					file.delete();
					System.out.println("잘못된 이미지 파일");
					rttr.addFlashAttribute("msgType", "실패 메세지");
					rttr.addFlashAttribute("msg", "이미지 파일만 업로드 가능합니다. (PNG, JPG, GIF)");				
					return "redirect:/imageForm.do";					
					
				}
			}
		}
		
		
		// 로그인 한 사람의 아이디 값을 가져와서 해당하는 아이디에 이미지 값을 넣어줌
		// 아이디 값은 session에 저장되어 있음 --> session에서 가져오기
		//   ---> 이미지 삭제를 구현하기 위해 위에서 코드 실행함 (String memId ~)
		
		
		// 업로드한 파일의 이름을 가져오는 코드 - input 태그의 name 값을 넣어줌
		String newProfile = multi.getFilesystemName("memProfile");
		
		// memId와 newProfile을 mvo에 저장해주기
		Member mvo = new Member();
		mvo.setMemId(memId);
		mvo.setMemProfile(newProfile);
		
		mapper.profileUpdate(mvo);
		
		// 로그인한 정보는 session에서 유지되고 있음 -> 회원정보 수정 시 session도 업데이트 해줘야함
		//  - 사진 업데이트 후 수정된 회원정보를 다시 가져와서 session에 담기
		//  - getMember를 통해 회원정보를 다시 가져와서(memId를 알고있으므로 가능) session에 담아준다
		Member m = mapper.getMember(memId);	
		session.setAttribute("mvo", m);
		
		// 위의 코드들이 모두 성공적으로 실행 됨
		System.out.println("잘못된 이미지 파일");
		rttr.addFlashAttribute("msgType", "성공 메세지");
		rttr.addFlashAttribute("msg", "프로필이 등록되었습니다.");				
		return "redirect:/";		
	}
	
}
