package com.nsnt.cosmos.db.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;

import org.hibernate.annotations.CreationTimestamp;

import com.nsnt.cosmos.api.request.StudyManagePostReq;
import com.nsnt.cosmos.api.request.StudyPostReq;

/**
 * 유저 모델 정의.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter

public class StudyManage {
	@EmbeddedId
	private StudyManageId studymangeId;
	
	@Column(length = 2000, name = "studymanage_notice")
	String studymanageNotice;

	@Column(columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime createdAt; // 생성일자
	
	public void updateStudyManage(StudyManagePostReq studyManageUpdateInfo) {
		this.studymanageNotice = studyManageUpdateInfo.getStudymanageNotice();
		this.createdAt = LocalDateTime.now();
		
	}
	
}
