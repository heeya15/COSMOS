package com.nsnt.cosmos.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
}
