package com.nsnt.cosmos.api.service;

import java.util.List;

import com.nsnt.cosmos.api.request.CommentReq;
import com.nsnt.cosmos.db.entity.Comment;

/**
 * 스터디 모집 게시판의 게시글에 달리는 댓글 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface CommentService {
	/** 댓글을 생성하는 createComment 입니다. */
	public Comment createComment(CommentReq commentReq);
	
	/** 모든 댓글 정보를 가져오는 finaAllComment 입니다. */
	public List<Comment> findAllComment();
	
	/** 댓글 수정을 위한 updateComment 입니다. */
	public Comment updateComment(Comment comment, CommentReq commentReq);
	
	/** 댓글 삭제를 위한 deleteComment 입니다. */
	public void deleteComment(Comment comment);
}
