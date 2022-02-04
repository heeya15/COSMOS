package com.nsnt.cosmos.db.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsnt.cosmos.api.request.StudyPostReq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 스터디 모델 정의.
 */
@Entity
@Table(
		name="study",
		uniqueConstraints={
			@UniqueConstraint(
				name ="STUDYNAME_URL_UNIQUE",
				columnNames={"study_name","url"}
			)
		}	
) 
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor /** Cannot construct instance of~ error를 해결해주었다.  파라미터가 없는 생성자 만들어줌* */ 
public class Study {

	@Id
	@Column(name = "study_no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long studyNo; // 번호
	
	@Column(name="study_name",length = 100)
	String studyName;
	
	@Column(name="url", length = 100)
	String url;
	
	
	@Column(name="image",length = 150, nullable = false)
	String image;
	
	@Column(nullable = false)
	@ColumnDefault("0")
	int totalMember; 	// 총 인원
	
	@Column(nullable = false)
	@ColumnDefault("0")
	int numberOfMember; // 인원
	
	@Column(columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="study_rule",length = 500)
	String studyRule; // 스터디 규칙
	
	@Column(name="study_password",length = 10, nullable = true)
	String studyPassword;
	
	@ManyToOne
	@JoinColumn(name = "privatestudyroom_id")
	
	private PrivateStudyRoom privateStudyRoom;
	
	@OneToOne
	@JoinColumn(name = "studytype_no")
	private StudyType studyType;
	
	public void updateStudy(StudyPostReq studyUpdateInfo) {
		this.studyName = studyUpdateInfo.getStudyName();
		this.url = studyUpdateInfo.getUrl();
		this.image = studyUpdateInfo.getImage();
		this.totalMember = studyUpdateInfo.getTotalMember();
		this.studyType.studytypeNo = studyUpdateInfo.getStudytypeNo();
		this.studyRule = studyUpdateInfo.getStudyRule();
		this.studyPassword = studyUpdateInfo.getStudyPassword();
		
	}
}

