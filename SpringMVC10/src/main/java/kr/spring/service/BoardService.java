package kr.spring.service;

import java.util.List;

import kr.spring.entity.Board;

public interface BoardService {

	
	public List<Board> getList();  // 게시글 전체조회
	public void register(Board vo);  // 게시글 등록 기능 - Board 타입의 매개변수 필요 
	public Board get(long idx);
	
	
}
