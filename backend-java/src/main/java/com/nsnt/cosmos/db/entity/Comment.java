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
	
	@Lob
	@Column(name = "content")
	private String content;  // 내용

	@Column
	@CreationTimestamp
	private LocalDateTime createdAt; // 생성 일자
	
	@Column
	@CreationTimestamp  
	private LocalDateTime updateAt; // 수정 일자
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")	
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "content_no")	
	private Board board;
}
