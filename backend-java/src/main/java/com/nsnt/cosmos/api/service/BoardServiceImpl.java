package com.nsnt.cosmos.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsnt.cosmos.api.request.SaveBoardDto;
import com.nsnt.cosmos.db.entity.Board;
import com.nsnt.cosmos.db.repository.BoardRepository;
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired // 이거를 jpa repository일 경우 해줘야함 -> 아니면 null 에러
	private BoardRepository boardRepository;

	/** 게시글을 생성하는 createBoard 입니다. */
	@Override
	public Board createBoard(SaveBoardDto saveBoardDto) {
		System.out.println("들어옴?");
		Board board = boardRepository.save(saveBoardDto.toEntity());
		return board;
	}

	/** 게시글 하나의 정보를 가져오는 findByBoardId 입니다. */
	@Override
	public Board findByBoardId(Long board_no) {
		Board board = boardRepository.findById(board_no).get();
		return board;
	}

	/** 모든 게시글의 정보를 가져오는 findAllBoard 입니다. (목록 부분에 사용) */
	@Override
	public List<Board> findAllBoard() {
		  List<Board> boards = boardRepository.findAll();
	      return boards;
	}

	/** 게시글을 수정하는 updateBoard 입니다. */
	@Transactional  // 이 어노테이션을 해줘야 업데이트 반영 됨.
	@Override
	public Board updateBoard(Board board, SaveBoardDto saveBoardDto) {
		System.out.println("찍히나?"+saveBoardDto.toString());  
		board.updateBoard(saveBoardDto);
	    return board;
	}
	@Transactional
	@Override
	public void deleteBoard(Board board) {
		boardRepository.delete(board);
	}
}
