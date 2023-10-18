package kr.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.entity.Board;
import kr.spring.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepository;  // Board mapper;와 유사 (DB와 관련된 일 수행)
	
	@Override
	public List<Board> getList() {
		List<Board> list = boardRepository.findAll();   // 알아서 SELECT문 실행
		return list;
	}

	@Override
	public void register(Board vo) {
		boardRepository.save(vo);  // 알아서 INSERT문 실행
		
	}

	@Override
	public Board get(Long idx) {
		Optional<Board> vo = boardRepository.findById(idx);  // pk 값을 기준으로 찾겠다 (pk == Id)
		return vo.get();
	}

	@Override
	public void delete(Long idx) {
		boardRepository.deleteById(idx);
		
	}

	@Override
	public void update(Board vo) {
		// JPA -> save() :  새로운 PK값 또는 없는 값이 들어온다면 INSERT
		//                  기존에 존재하는 PK 값이 들어온다면 UPDATE 기능 실행
		boardRepository.save(vo);  
		
	}

}
