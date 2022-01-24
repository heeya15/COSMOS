package com.nsnt.cosmos.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsnt.cosmos.api.request.CommentReq;
import com.nsnt.cosmos.db.entity.Comment;
import com.nsnt.cosmos.db.repository.CommentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("CommentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	/** 댓글을 생성하는 createComment 입니다. */
	@Override
	@Transactional
	public Comment createComment(CommentReq commentReq) {
		log.debug(">>>>>>>>>>>>>>>>>>>>> createComment : {}", commentReq);
		Comment comment = commentRepository.save(commentReq.toEntity());
		return comment;
	}

	/** 모든 댓글 정보를 가져오는 finaAllComment 입니다. */
	@Override
	public List<Comment> findAllComment() {
		log.debug(">>>>>>>>>>>>>>>>>>>>> findAllComment");
		List<Comment> comments = commentRepository.findAll();
		return comments;
	}

	/** 댓글 수정을 위한 updateComment 입니다. */
	@Override
	public Comment updateComment(Comment comment, CommentReq commentReq) {
		log.debug(">>>>>>>>>>>>>>>>>>>> updateComment");
		
		return comment;
	}

	/** 댓글 삭제를 위한 deleteComment 입니다. */
	@Override
	public void deleteComment(Comment comment) {
		commentRepository.delete(comment);
	}
}
