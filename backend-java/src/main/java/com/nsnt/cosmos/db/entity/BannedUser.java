package com.nsnt.cosmos.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * 강퇴자 모델 정의.
 */
@Entity
@Getter
@Setter
public class BannedUser {
	@Id
	@Column(name = "banneduser_no", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 해당 속성에 auto_increment 제약조건 추가
	int banneduserNo;
	
	@Column(name = "user_id", length = 20, nullable = false)
	String userId;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "publicstudyroom_id", referencedColumnName = "publicstudyroom_id")
	})
	private PublicStudyRoom publicStudyRoom;
}
