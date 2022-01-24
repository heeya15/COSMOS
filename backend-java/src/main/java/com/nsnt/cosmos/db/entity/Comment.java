package com.nsnt.cosmos.db.entity;

import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.nsnt.cosmos.api.request.CommentReq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 댓글 모델 정의.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment{
	@Id
	@Column(name = "comment_no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentNo; // 댓글 번호
	
	@Column(name = "content")
	private String content;  // 내용

	@Column
	@CreationTimestamp
	private LocalDateTime createdAt; // 등록 일자 (최초 생성 및 수정)
		
	@ManyToOne
	@JoinColumn(name = "user_id")	
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "board_no")	
	private Board board;
	
	public void updateComment(CommentReq commentReq) {
		this.commentNo = commentReq.getComment_no();
		this.content = commentReq.getContent();
		this.createdAt = commentReq.getCreated_at();
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", content=" + content + ", createdAt=" + createdAt + ", user="
				+ user + ", board=" + board + "]";
	}
	
	
}
