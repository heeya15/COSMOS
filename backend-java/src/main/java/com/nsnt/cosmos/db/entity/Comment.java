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

import lombok.Getter;
import lombok.Setter;

/**
 * 댓글 모델 정의.
 */
@Entity
@Getter
@Setter
public class Comment{
	@Id
	@Column(name = "comment_no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long commentNo; // 댓글 번호
	
	@Lob
	@Column(name = "content")
	Blob content;  // 내용

	@Column
	@CreationTimestamp
	LocalDateTime createdAt; // 생성 일자
	
	@Column
	@CreationTimestamp  
	LocalDateTime updateAt; // 수정 일자
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")	
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "content_no")	
	private Board board;
}
