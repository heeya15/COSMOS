package com.nsnt.cosmos.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * 비공개 스터디 룸 히스토리 모델 정의.
 */
@Entity
@Getter
@Setter
public class PrivateStudyRoomHistory {
	@Id
	@Column(name = "privatestudyroomhistory_no", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 해당 속성에 auto_increment 제약조건 추가
	int privatestudyroomhistoryNo;
	
	@Column(name = "privateroom_open_time", columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime privateroomOpenTime;
	
	@Column(name = "privateroom_close_time", columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime privateroomCloseTime;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "privatestudyroom_id", referencedColumnName = "privatestudyroom_id")
	})
	private PrivateStudyRoom privateStudyRoom;
}
