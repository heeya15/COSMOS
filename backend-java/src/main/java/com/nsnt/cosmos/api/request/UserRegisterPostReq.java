package com.nsnt.cosmos.api.request;

import java.sql.Timestamp;

import javax.validation.constraints.Email;

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
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
	String userId;
	String userName;
	@ApiModelProperty(name="유저 Password", example="your_password")
	String userPassword;
	
	
	private String userEmail;
	int totalTime;
	int totalAttendance;
	
//	Timestamp joinDate;
}
