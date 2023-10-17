package kr.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.spring.entity.Board;

@Repository  // 메모리로 올리기 위한 어노테이션 (생략가능)
public interface BoardRepository extends JpaRepository<Board, Long>{
	// BoardMapper와 같음
	// interface도 interface를 상속받을 수 있음  -- JpaRepository<테이블명(리턴값), PK 데이터타입>
	// 3Tier : BoardController <-> BoardService <-> BoardRepository (BR : 자체적으로 쿼리문을 만들어서 db에 넣거나 가져와 돌려줌)
	
	
	
}
