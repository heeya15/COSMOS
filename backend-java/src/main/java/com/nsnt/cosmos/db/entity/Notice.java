package com.nsnt.cosmos.db.entity;

import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class Notice{
	@Id
	@Column(name = "content_no", length= 20)
	String contentNo;
	
	@Column(name = "content_title", length= 60)
	String contentTitle;
	
	@Column(name = "header", length= 30)
	Boolean header;
	
	@Column(length = 200, nullable = false)
	int recruitNumber;
	
	@Lob
	@Column(name = "content")
	Blob content;
	
	@Column(name = "content_status", length= 30)
	Boolean contentStatus;
	
	@Column
	@CreationTimestamp
	LocalDateTime createdAt;
	
	@Column
	@CreationTimestamp
	LocalDateTime updateAt;
	
	
	@Column(name = "study_name", length= 60)
	String studyName;
	
	
	@Column(name = "studytype_name", length= 50)
	String studytypeName;
	
}
