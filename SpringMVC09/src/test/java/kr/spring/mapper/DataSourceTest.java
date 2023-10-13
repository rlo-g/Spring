package kr.spring.mapper;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.spring.entity.Board;
import kr.spring.entity.Criteria;
import kr.spring.service.BoardService;
import kr.spring.service.BoardServiceImpl;
import lombok.extern.log4j.Log4j;

@Log4j   // 테스트 실행결과를 콘솔창에 나오게 하기 위함
@RunWith(SpringJUnit4ClassRunner.class)   // 실행하기 위해서 스프링 컨테이너에 올리는 코드
@WebAppConfiguration    // Servlet 컨테이너(Controller)를 사용하기 위한 어노테이션

// root-context.xml 경로를 잡아주는 과정
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})    
public class DataSourceTest {
	// root_context.xml이 이상이 없는지 test하는 클래스
	
	// Connection이 잘 되는지 테스트
	// Hikari config (커넥션 하기위한 정보를 가짐) dataSource(히카리를 통해 커넥션), sessionFactoryBean(데이터 소스를 가지고 jdbc 실행)
	// 커넥션 하는 객체
	@Autowired // root-context.xml에 있는 id가 dataSource인 녀석을 사용하겠다.
	private DataSource dataSource;

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private BoardServiceImpl service;
	
	@Autowired
	private WebApplicationContext ctx;  // Spring Container 메모리 공간 객체
	
	private MockMvc mockMvc;  // 가상의 MVC 환경을 만들어주는 객체 - view, handler, mapping 등을 실행해줌
	
	@Before // Test 실행 전에 먼저 실행하는 부분
	public void setup() {
		// mockMvc 가상 환경에 현재 환경에 대한 정보 ctx를 빌드(로딩)
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	
	// 
//	@Test
//	public void testInsert() {
//		Board vo = new Board();
//		vo.setMemID("hammm");
//		vo.setTitle("배고파");
//		vo.setContent("자고싶다");
//		vo.setWriter("햄햄");
//		mapper.insertSelectKey(vo);
//	}
	
//	@Test
//	public void testInsert() {
//		Board vo = new Board();
//		vo.setMemID("ppp");
//		vo.setTitle("졸려");
//		vo.setContent("자고싶다");
//		vo.setWriter("ㅠㅠ");
//		mapper.insert(vo);
//	}
	
	
	// controller에 대한 테스트
//	@Test
//	public void testController() throws Exception{
//		log.info(
//				mockMvc.perform(MockMvcRequestBuilders.get("/board/modify?idx=3"))  //  해당 url을 perform(요청)
//				.andReturn()  // return 값을 받아오겠다
//				.getModelAndView()  // controller의 model 값과 view 경로를 다 받아오겠다
//				);
//	}
//	
	
	
	// service 클래스 안에 있는 getList가 잘 되는지 테스트
	@Test
	public void testGetList() {
		Criteria cri = new Criteria();
		List<Board> list = service.getList(cri);
		for(Board vo : list) {
			System.out.println(vo.toString());			
		}

	}
	
	
	// Mapper를 통한 테스트
//	@Test
//	public void testGetList() {
//		List<Board> list = mapper.getList();
//		for(Board vo : list) {
//			System.out.println(vo.toString());
//		}
//	}
	
	
	// root-context에 대한 테스트
//	@Test
//	public void testConnection() {
//		try( Connection conn =  dataSource.getConnection() ){
//			// 커넥션 정보 가져오기
//			log.info(conn);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
}
