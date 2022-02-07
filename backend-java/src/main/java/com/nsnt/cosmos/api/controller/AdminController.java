package com.nsnt.cosmos.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsnt.cosmos.api.response.UserDtoRes;
import com.nsnt.cosmos.api.service.UserService;
import com.nsnt.cosmos.common.auth.SsafyUserDetails;
import com.nsnt.cosmos.db.entity.Comment;
import com.nsnt.cosmos.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "유저 API", tags = { "Admin" })
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	UserService userService;

	 /** 유저 전체 조회 입니다. */
	@ApiOperation(value="유저 전체 조회", notes="<strong>유저 전체 조회를</strong>시켜줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	@GetMapping("/searchAll")
    public ResponseEntity<List<User>> findAllUser(){
        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }	

	/** 유저 삭제 */
	@GetMapping("/remove/{user_id}")
	@ApiOperation(value = "해당 유저 삭제", notes = "선택한 유저를 삭제한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> deleteUser(@PathVariable String user_id) {
		User user;
		try {
			user = userService.getUserByUserId(user_id);
			userService.deleteByUserId(user);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResponseEntity.status(500).body("해당 유저가 없어어 삭제 "+FAIL);
		}
		return ResponseEntity.status(200).body("유저 아이디 " +user.getUserId()+"삭제 "+SUCCESS);
	}
}
