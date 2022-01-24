package com.nsnt.cosmos.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * 비공개 스터디 참가자 모델 정의.
 */
@Entity
@Getter
@Setter
public class PrivateMember {
	@Id
	@Column(name = "privatemember_no", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 해당 속성에 auto_increment 제약조건 추가
	private int privatememberNo;
	
	@Column(name = "user_id")
	private String userId;
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "privatestudyroom_id", referencedColumnName = "privatestudyroom_id"),
	})
	private PrivateStudyRoom privateStudyRoom;
}