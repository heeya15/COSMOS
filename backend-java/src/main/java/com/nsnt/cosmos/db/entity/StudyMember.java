package com.nsnt.cosmos.db.entity;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

/**
 * 스터디 멤버 모델 정의.
 */
@Entity
@Getter
@Setter
public class StudyMember{
	
	@Id
	@Column(name = "studymember_no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long studymemberNo; // 번호
	
	
	@Column(name = "leader")
	@ColumnDefault("0")
	boolean leader;
	
	@Column(name = "authority")
	@ColumnDefault("0")
	boolean authority;
	
	@ColumnDefault("0")
	int score; // 숫자
	
	@Column(name = "attendance")
	@ColumnDefault("0")
	boolean attendance;
	
	@ColumnDefault("0")
	int studytime; // 공부 시간
	
	@ManyToOne
	@JoinColumn(name = "user_id")	
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "study_no")	
	private Study study;
}
