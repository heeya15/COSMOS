package com.nsnt.cosmos.api.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nsnt.cosmos.api.request.SaveBoardDto;
import com.nsnt.cosmos.api.response.StudyNameSearchDtoRes;
import com.nsnt.cosmos.api.service.BoardService;
import com.nsnt.cosmos.api.service.PrivateRoomService;
import com.nsnt.cosmos.api.service.StudyService;
import com.nsnt.cosmos.common.auth.SsafyUserDetails;
import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.Board;
import com.nsnt.cosmos.db.entity.PrivateMember;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 비공개 스터디 룸 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "비공개 스터디 룸 API", tags = { "PrivateRoom" })
@RestController
@RequestMapping("/api/privateroom")
public class PrivateRoomController {
	public static final Logger logger = LoggerFactory.getLogger(PrivateRoomController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	PrivateRoomService privateRoomService;
	
	@PostMapping("/register")
	@ApiOperation(value="비공개 스터디 참가자 등록 (token)(param)", notes="<strong>비공개 스터디 참가자  등록</strong>시켜줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity PrivateRoomRegister(@RequestParam String privatestudyroom_id , @ApiIgnore Authentication authentication)
	{
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_id = userDetails.getUsername();
		
		PrivateMember pvmember;
		try {
			pvmember = privateRoomService.createPrivateMember(privatestudyroom_id, user_id);
		}catch(Exception E) {
			E.printStackTrace();
			System.out.println("비공개 스터디 참가자 추가 실패");
			return  ResponseEntity.status(500).body("디비 트랜잭션 오류로 인한 생성 실패");
		}
		System.out.println("잘 됨?"+ pvmember.toString());
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	 /** 해당 비공개 스터디 참가자 전체 조회 입니다. */
	@ApiOperation(value="해당 비공개 스터디 참가자 전체 조회(param)", notes="<strong>해당 비공개 스터디 참가자  전체 조회를</strong>시켜줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	@GetMapping("/searchAll")
    public ResponseEntity<List<PrivateMember>> findAllPrivateMember(@RequestParam String privatestudyroom_id){
        List<PrivateMember> privatemember = privateRoomService.findAllPrivateMember(privatestudyroom_id);

        return new ResponseEntity<List<PrivateMember>>(privatemember,HttpStatus.OK);
    }
	
	/** 해당 비공개 스터디 참가자 명단 삭제 **/
	@ApiOperation(value = "해당 비공개 스터디 참가자가 방에서 나갈경우 명단 삭제", notes = "해당 비공개 스터디 참가자가 방에서 나갈경우 명단 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "해당 비공개 스터디 참가자 명단 삭제 성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "해당 회원 없음")})
	@DeleteMapping("/remove/privateMember")
	public ResponseEntity<String> removePrivateMember(@RequestParam String privatestudyroom_id,  @ApiIgnore Authentication authentication) throws Exception {	
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_id = userDetails.getUsername();
		try {	
			privateRoomService.deletePrivateMember(privatestudyroom_id, user_id);
		}catch(Exception e ) {
			e.printStackTrace();
			System.out.println("해당 비공개 스터디 참가자  실패");
			return  ResponseEntity.status(500).body("해당 비공개 스터디 참가자 제거 "+FAIL);
		}
		logger.debug("해당 비공개 스터디 참가자 명단에서 해당 user 삭제 성공");
		return ResponseEntity.status(200).body(user_id+"가 방에 나가서 비공개 스터디 멤버에서 제거 함"+SUCCESS);
	}
}