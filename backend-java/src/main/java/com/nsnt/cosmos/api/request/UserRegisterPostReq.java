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
	@Pattern(regexp = "^[0-9a-zA-Z가-힣]*$", message = "닉네임은 숫자, 영어, 한글만 가능합니다.")
	@Size(min = 4, max = 12)
	String userId;
	
	@ApiModelProperty(name="유저 Name", example="kimssafy")
	String userName;
	
	
	@ApiModelProperty(name="유저 Password", example="ssafy11!")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,12}$",
    message = "비밀번호는 영문, 숫자, 특수문자가 적어도 1개 이상씩 포함된 8자 ~ 12자의 비밀번호여야 합니다.")
	String userPassword;
	
	@ApiModelProperty(name="유저 Email", example="ssafy@ssafy.com")
	@Email(message = "이메일 형식이 아닙니다.")
	private String userEmail;
	
	int totalTime;
	
	int totalAttendance;
	
//	Timestamp joinDate;
}
