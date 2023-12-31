package kr.spring.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


import kr.spring.entity.Board;

@Mapper  // MyBatis가 이 interface를 찾을 수 있도록 달아주는 부분
public interface BoardMapper {
	// 추상메서드 
	// 1.추상클래스 (추상 메서드 선언, 상속)  2. 인터페이스  --> 인터페이스 파일 사용
	// 객체를 생성할 수 없다
	/* MyBatis는 어떻게 boardmapper를 찾아오는가?  
	 *   --> root-context.xml - mybatis-spring:scan --> 해당 패키지에서 @Mapper가 달린 곳 */

	public List<Board> getLists();   // 게시글 전체보기 기능

	public void boardInsert(Board board);   // values(?,?,?) --> values(#{필드명}, #{필드명})

	public Board boardContent(int idx);

	public void boardDelete(int idx);

	public void boardUpdate(Board vo);

	public void boardCount(int idx);
	

	
	

}
