package com.nsnt.cosmos.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 비공개 스터디 룸 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class PrivateStudyRoom {
	@Id
	@Column(name = "privatestudyroom_id",  nullable = false)
	private String privatestudyroomId;
	
	@OneToOne
	@JoinColumn(name = "study_no", referencedColumnName = "study_no")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Study study;
}
