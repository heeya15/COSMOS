package com.nsnt.cosmos.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 비공개 스터디 룸 모델 정의.
 */
@Entity
@Getter
@Setter
public class PrivateStudyRoom {
	@Id
	@Column(name = "privatestudyroom_id", length= 10,  nullable = false)
	private String privatestudyroomId;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "study_no", referencedColumnName = "study_no")
	})
	private Study study;
}
