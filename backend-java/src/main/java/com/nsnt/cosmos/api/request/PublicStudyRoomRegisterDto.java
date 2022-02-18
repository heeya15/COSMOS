package com.nsnt.cosmos.api.request;

import com.nsnt.cosmos.db.entity.PublicStudyRoom;
import com.nsnt.cosmos.db.entity.StudyType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor /** Cannot construct instance of~ error를 해결해주었다.  파라미터가 없는 생성자 만들어줌* */ 
@ToString
public class PublicStudyRoomRegisterDto {
	@ApiModelProperty(name="공개스터디룸 아이디", example="")
	String publicstudyroomId;
	
	@ApiModelProperty(name="스터디 이름", example="")
	String studyName;
	
	@ApiModelProperty(name="스터디 url", example="")
	String url;
	
	@ApiModelProperty(name="대표 이미지", example="")
	String image;

	@ApiModelProperty(name="총 인원", example="10")
	int numberOfMember; 	// 총 인원

	@ApiModelProperty(name="스터디 규칙", example="")
	String studyRule; // 스터디 규칙
		
	@ApiModelProperty(name="스터디 분류 번호", example="1")
	int studytypeNo;
	
	 public PublicStudyRoom toEntity(){
		 	StudyType studyType = new StudyType();
			studyType.setStudytypeNo(studytypeNo);
	        return PublicStudyRoom.builder()
	        		.publicstudyroomId(publicstudyroomId)
	        		.studyName(studyName)
	        		.url(url)
	        		.image(image)
	        		.numberOfMember(numberOfMember)
	        		.studyRule(studyRule)
	        		.studyType(studyType)
	                .build();
	    }
}
