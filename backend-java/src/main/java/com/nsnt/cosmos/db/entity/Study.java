package com.nsnt.cosmos.db.entity;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * 스터디 모델 정의.
 */
@Entity
@Table(
		name="Study",
		uniqueConstraints={
			@UniqueConstraint(
				name ="STUDYNAME_URL_UNIQUE",
				columnNames={"study_name","url"}
			)
		}	
) 
@Getter
@Setter
public class Study {

	@Id
	@Column(name = "study_no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long studyNo; // 번호
	
	@Column(name="study_name",length = 100)
	String studyName;
	
	@Column(name="url", length = 100)
	String url;
	
	
	@Column(name="image",length = 150, nullable = false)
	String image;
	
	@Column(nullable = true)
	int totalMember; 	// 총 인원
	@Column(nullable = true)
	int numberOfMember; // 인원
	
	@Column(columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="study_rule",length = 500)
	String studyRule; // 스터디 규칙
	
	@Column(name="study_password",length = 10, nullable = true)
	String studyPassword;
	
	@ManyToOne
	@JoinColumn(name = "privatestudyroom_id")	
	private PrivateStudyRoom privateStudyRoom;
}
