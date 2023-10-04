package kr.spring.mapper;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.spring.entity.Board;
import lombok.extern.log4j.Log4j;

@Log4j // 테스트 실행결과를 콘솔창에 나오게 하기 위함
@RunWith(SpringJUnit4ClassRunner.class) // 실행하기 위해서 스프링 컨테이너에 올리는 코드
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")  // root-context.xml 경로를 잡아주는 과정
public class DataSourceTest {
	// root_context.xml이 이상이 없는지 test하는 클래스
	
	// Connection이 잘 되는지 테스트
	// Hikari config (커넥션 하기위한 정보를 가짐) dataSource(히카리를 통해 커넥션), sessionFactoryBean(데이터 소스를 가지고 jdbc 실행)
	// 커넥션 하는 객체
	@Autowired // root-context.xml에 있는 id가 dataSource인 녀석을 사용하겠다.
	private DataSource dataSource;

	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		List<Board> list = mapper.getList();
		for(Board vo : list) {
			System.out.println(vo.toString());
		}
	}
	
	
	
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
