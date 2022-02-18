package com.nsnt.cosmos.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 스터디 신청 모델 정의.
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyMember {

	@Id
	@Column(name = "applymember_no", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int applymemberNo;
	
	@ManyToOne
	@JoinColumn(name = "study_no")
	@OnDelete(action = OnDeleteAction.CASCADE)
	Study study_no;

	// FK
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	User userId;
		
}
