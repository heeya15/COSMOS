package com.nsnt.cosmos.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsnt.cosmos.api.service.UserHistoryService;
import com.nsnt.cosmos.common.auth.SsafyUserDetails;
import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.UserHistory;
import com.nsnt.cosmos.db.entity.UserHistoryDay;

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
	UserHistoryService historyService;
	
	/** 비공개 스터디 룸 입장 시간 등록 */
	@PostMapping("/register/private/starttime")
	@ApiOperation(value = "사용자 history 등록 (token)", notes = "<strong>사용자 비공개 스터디룸 이용 시작시, 사용자 history 등록</strong>")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<UserHistory> setPublicStartTime(@ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_Id = userDetails.getUsername();

		UserHistory userhistory = historyService.setPrivateStartTime(user_Id);
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		return new ResponseEntity<UserHistory>(userhistory,HttpStatus.OK);
	}
	
	/** 비공개 스터디 룸 퇴장 시간 등록 */
	@PostMapping("/register/private/finishtime")
	@ApiOperation(value = "사용자 history finish time 갱신 (token)(param)", notes = "<strong>사용자 비공개 스터디룸 이용 종료시, 사용자 history finish time 갱신</strong>")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> setPublicFinishTime(@RequestParam("userhistory_no") Long userhistory_no, @ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_Id = userDetails.getUsername();

		historyService.setPrivateUserFinishTime(user_Id, userhistory_no);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	/** 공개 스터디 룸 입장 시간 등록 */
	@PostMapping("/register/public/starttime")
	@ApiOperation(value = "사용자 history 등록 (token)(param)", notes = "<strong>사용자 공개 스터디룸 이용 시작시, 사용자 history 등록</strong>")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<UserHistory> setPrivateStartTime(@RequestParam("start_time") String start_time, @ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_Id = userDetails.getUsername();

		System.out.println(">>>>>>>>>>> UserHistoryController : "+ start_time + " && "  + user_Id);
		
		UserHistory userhistory = historyService.setPublicUserStartTime(start_time, user_Id);
		return new ResponseEntity<UserHistory>(userhistory,HttpStatus.OK);
	}
	
	/** 공개 스터디 룸 퇴장 시간 등록 */
	@PostMapping("/register/public/finishtime")
	@ApiOperation(value = "사용자 history finish time 갱신 (token)(param)", notes = "<strong>사용자 공개 스터디룸 이용 종료시, 사용자 history finish time 갱신</strong>")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> setPrivateFinishTime(
			@RequestParam("userhistory_no") Long userhistory_no, @RequestParam("finish_time") String finish_time, @ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_Id = userDetails.getUsername();

		historyService.setPublicUserFinishTime(finish_time, userhistory_no, user_Id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	/** 일별 랭킹 검색 */
	@GetMapping("searchAll/day")
	@ApiOperation(value = "사용자 일별 history 전체 조회", notes = "<strong>사용자 일별 history 전체 목록</strong>을 조회합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<List<UserHistoryDay>> getDailyUserHistory() {

		List<UserHistoryDay> userhistoryday = historyService.getDailyUserHistory();
		return new ResponseEntity<List<UserHistoryDay>>(userhistoryday,HttpStatus.OK);
	}
	
	/** 월별 랭킹 검색 */
	
	/** 일별 랭킹 검색 */
}
