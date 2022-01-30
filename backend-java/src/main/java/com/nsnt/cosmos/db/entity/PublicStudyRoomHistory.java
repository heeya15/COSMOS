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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * 공개 스터디 룸 히스토리 모델 정의.
 */
@Entity
@Getter
@Setter
public class PublicStudyRoomHistory {
	@Id
	@Column(name = "publicstudyroomhistory_no", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 해당 속성에 auto_increment 제약조건 추가
	int publicstudyroomhistoryNo;
	
	@Column(columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
	@CreationTimestamp
	private LocalDateTime publicroomOpenTime;
	
	@Column(columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
	@CreationTimestamp
	private LocalDateTime publicroomCloseTime;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "publicstudyroom_id", referencedColumnName = "publicstudyroom_id")
	})
	private PublicStudyRoom publicStudyRoom;
}
