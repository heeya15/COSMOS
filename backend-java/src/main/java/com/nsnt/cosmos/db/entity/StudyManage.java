package com.nsnt.cosmos.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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
	
	@Lob
	@Column(name = "studymanage_notice")
	Blob studymanageNotice;

	@Column(columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime createdAt; // 생성일자
	
	@Column(columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime updatedAt; // 수정일자
	
}
