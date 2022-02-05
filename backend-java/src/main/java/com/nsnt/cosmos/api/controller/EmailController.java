package com.nsnt.cosmos.api.controller;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsnt.cosmos.api.service.EmailService;
import com.nsnt.cosmos.api.service.UserService;
import com.nsnt.cosmos.common.model.response.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 이메일 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "이메일  API", tags = { "Email" })
@RestController
@RequestMapping("/api/email")
public class EmailController {
	public static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private EmailService emailService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/emailcheck/{user_email}")
	@ApiOperation(value = "회원 이메일 중복 체크", notes = "회원가입 시 회원 이메일 중복 체크 검사. "
			+ "이메일이 중복인 경우 false, 이메일이 중복이 아닌 경우 true를 리턴한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") 
					})
	public ResponseEntity<Boolean> emailCheck(@PathVariable String user_email) {
		boolean emailChk = userService.checkUserEmail(user_email);
		
		if (emailChk) {
			return ResponseEntity.status(200).body(emailChk);
		} else
		return ResponseEntity.status(401).body(emailChk);
	}
	
	@GetMapping("/send/{user_email}")
	@ApiOperation(value = "이메일 전송", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<String> sendMail(@PathVariable String user_email) throws MessagingException {
		return ResponseEntity.status(401).body(emailService.sendMail(user_email));
	}
	
	
}
