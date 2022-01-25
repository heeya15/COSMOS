package com.nsnt.cosmos.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	// 해당 게시글의 모든 댓글 조회
	@Query(value = "select * from Comment where board_no = :board_no "
			, nativeQuery = true)
	List<Comment> findAllByBoardNo(@Param("board_no") Long board_no);
}