package com.nsnt.cosmos.api.service;

import java.util.List;

import com.nsnt.cosmos.api.request.SaveBoardDto;
import com.nsnt.cosmos.db.entity.Board;


/**
 *	스터디 모집 게시판 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface BoardService {
	/** 게시글을 생성하는 createBoard 입니다. */
    public Board createBoard(SaveBoardDto saveBoardDto);
     
    /** 게시글 하나의 정보를 가져오는 findByBoardId 입니다. */
    public Board findByBoardId(Long string);

    /** 모든 게시글의 정보를 가져오는 findAllBoard 입니다. (목록 부분에 사용)*/
    public List<Board> findAllBoard();

    /** 게시글을 수정하는 updateBoard 입니다.*/
    public Board updateBoard(Board board, SaveBoardDto saveBoardDto);
      

    /** 게시글을 삭제하는 deleteBoard 입니다. */
    public void deleteBoard(Board board);
}
