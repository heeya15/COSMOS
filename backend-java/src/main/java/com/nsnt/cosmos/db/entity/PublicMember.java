package com.nsnt.cosmos.db.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PublicMember {
	@Id
	@Column(name = "publicmemeber_no", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 해당 속성에 auto_increment 제약조건 추가
	private int publicmemberNo;
	
	@Column(name = "attendance")
	@ColumnDefault("0")
	private boolean attenacne;
	
	@Column(name = "member_score")
	private int memberScore;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "publicstudyroom_id", referencedColumnName = "publicstudyroom_id"),
	})
	private PublicStudyRoom publicStudyRoom;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	})
	private User user;
}
