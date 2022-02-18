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
public class StudyManagePostReq {

	@ApiModelProperty(name="스터디 번호", example="")
	Long studyNo;

	@ApiModelProperty(name="공지사항 내용", example="")
	String studymanageNotice;

	
}
