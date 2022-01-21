package com.nsnt.cosmos.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StudyManageId implements Serializable {
	
	private static final long serialVersionUID = 1L;


	@Column(name = "study_manage_no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long studyManageNo; // 번호
	
	@OneToOne
	@JoinColumn(name = "study_no")
	Study studyNo;
}
