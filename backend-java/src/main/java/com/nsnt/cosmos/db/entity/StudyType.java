package com.nsnt.cosmos.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

/**
 * 스터디분류 모델 정의.
 */
@Entity
@Getter
@Setter
@Table(
	// 속성에 UNIQUE 제약조건 설정
	uniqueConstraints = {
		@UniqueConstraint(
			columnNames = {"studytype_name"}
		)
	}
)
public class StudyType {
	@Id
	@Column(name = "studytype_no", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 해당 속성에 auto_increment 제약조건 추가
	int studytypeNo;
	
	@Column(name = "studytype_name", length = 50, nullable = false)
	String studytypeName;
}
