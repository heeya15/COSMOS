package com.nsnt.cosmos.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsnt.cosmos.api.request.SaveUserHistoryDto;
import com.nsnt.cosmos.api.service.HistoryService;
import com.nsnt.cosmos.common.auth.SsafyUserDetails;
import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.UserHistory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;
/**
 * 유저 히스토리 관련 API 요청 처리를 위한 컨트롤러 정의.
*/
@Api(value = "유저 히스토리 관련 API", tags = { "UserHistory" })
@RestController
@RequestMapping("/api/history")
public class UserHistoryController {
	public static final Logger logger = LoggerFactory.getLogger(UserHistoryController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	HistoryService historyService;
	
	@PostMapping("/signup")
	@ApiOperation(value = "사용자 history 등록", notes = "<strong>사용자 history 등록</strong>")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> register(@RequestBody SaveUserHistoryDto userhisroyDto, @ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_Id = userDetails.getUsername();

		UserHistory history = historyService.insertUserHistory(userhisroyDto, user_Id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
