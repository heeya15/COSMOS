package com.nsnt.cosmos.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor /** Cannot construct instance of~ error를 해결해주었다.  파라미터가 없는 생성자 만들어줌* */ 
public class StudyPostReq {
	@ApiModelProperty(name="스터디 번호", example="")
	long studyNo;

	@ApiModelProperty(name="스터디 이름", example="")
	String studyName;
	
	@ApiModelProperty(name="스터디 url", example="")
	String url;
	
	@ApiModelProperty(name="대표 이미지", example="")
	String image;

	@ApiModelProperty(name="스터디 총 인원", example="10")
	int totalMember; 	// 총 인원

	@ApiModelProperty(name="스터디 규칙", example="")
	String studyRule; // 스터디 규칙
	
	@ApiModelProperty(name="스터디 비밀번호", example="")
	String studyPassword;
	
	@ApiModelProperty(name="스터디 분류", example="1")
	int studytypeNo;
	
}
