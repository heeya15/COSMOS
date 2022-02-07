package com.nsnt.cosmos.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
	@ApiModelProperty(name="유저 ID", example="ssafy1")
	String userId;
	
	@ApiModelProperty(name="유저 Name", example="kimssafy")
	String userName;
	
	@ApiModelProperty(name="유저 Password", example="ssafy11!")
	String userPassword;
	
	@ApiModelProperty(name="유저 Email", example="ssafy@ssafy.com")
	@Email(message = "이메일 형식이 아닙니다.")
	private String userEmail;
	
	int totalTime;
	
	int totalAttendance;
	
//	Timestamp joinDate;
}
