package com.nsnt.cosmos.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.api.response.CommentSearchDtoRes;
import com.nsnt.cosmos.db.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	// 해당 게시글의 모든 댓글 조회
	@Query(value = "select c.comment_no, c.content, c.created_at, c.user_id, c.board_no\r\n" + 
            "from comment c join user u\r\n" + 
            "on u.user_id = c.user_id\r\n" + 
            "join board b\r\n" + 
            "on b.board_no = c.board_no\r\n" + 
            "where c.board_no = :board_no "
            , nativeQuery = true)
	List<CommentSearchDtoRes> findAllByBoardNo(@Param("board_no") Long board_no);
}