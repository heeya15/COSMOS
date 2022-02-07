package com.nsnt.cosmos.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsnt.cosmos.api.request.PublicMemberRegisterDto;
import com.nsnt.cosmos.api.request.PublicStudyRoomRegisterDto;
import com.nsnt.cosmos.api.service.PublicRoomService;
import com.nsnt.cosmos.common.auth.SsafyUserDetails;
import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.PublicMember;
import com.nsnt.cosmos.db.entity.PublicStudyRoom;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 공개 스터디 룸 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "공개 스터디 룸 API", tags = { "PublicRoom" })
@RestController
@RequestMapping("/api/publicroom")
public class PublicRoomController {
	public static final Logger logger = LoggerFactory.getLogger(PublicRoomController.class);
	private static final String SUCCESS = "Success";
	private static final String FAIL = "fail";

	@Autowired
	PublicRoomService publicRoomService;
	
	@PostMapping("/registerpublicroom")
	@ApiOperation(value = "공개 스터디룸 생성 ", notes = "공개 스터디룸 생성")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "원하는 정보 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> PublicRoomRegister(@RequestBody PublicStudyRoomRegisterDto publicstudyroomdDto, @ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_id = userDetails.getUsername();
		
		PublicStudyRoom pubicstudyroom;
		try {
			System.out.println(publicstudyroomdDto.toString());
			pubicstudyroom = publicRoomService.createPublicStudyRoom(publicstudyroomdDto, user_id);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+pubicstudyroom.toString());
		}catch(Exception E) {
			E.printStackTrace();
			System.out.println("공개 스터디룸 생성 실패");
			return  ResponseEntity.status(500).body(BaseResponseBody.of(500, FAIL));
		}
		System.out.println("잘 됨?"+ pubicstudyroom.toString());
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, SUCCESS));
	}
	
	@PostMapping("/register/publicmember")
	@ApiOperation(value="공개 스터디 참가자 등록 (token)(param)", notes="<strong>공개 스터디 참가자  등록</strong>시켜줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "원하는 정보 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> publicMemberRegister(@RequestBody PublicMemberRegisterDto publicMemberDto, @ApiIgnore Authentication authentication)
	{
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_id = userDetails.getUsername();
		
		try {
			publicRoomService.createPublicMember(publicMemberDto, user_id);
		}catch(Exception E) {
			E.printStackTrace();
			System.out.println("공개 스터디 참가자 추가 실패");
			return  ResponseEntity.status(500).body(BaseResponseBody.of(500, FAIL));
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, SUCCESS));
	}
	
	
	 /** 해당 공개 스터디 참가자 전체 조회 입니다. */
	@ApiOperation(value="해당 공개 스터디 참가자 전체 조회(param)", notes="<strong>해당 공개 스터디 참가자  전체 조회를</strong>시켜줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "원하는 정보 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	@GetMapping("/search/publicmember")
    public ResponseEntity<List<PublicMember>> findAllPrivateMember(@RequestParam String publicstudyroom_id){
        List<PublicMember> publicmember = publicRoomService.findAllPublicMember(publicstudyroom_id);

        return new ResponseEntity<List<PublicMember>>(publicmember,HttpStatus.OK);
    }
	
	/** 해당 공개 스터디 참가자 명단 삭제 **/
	@ApiOperation(value = "해당 공개 스터디 참가자가 방에서 나갈경우 명단 삭제", notes = "해당 공개 스터디 참가자가 방에서 나갈경우 명단 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "해당 비공개 스터디 참가자 명단 삭제 성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "해당 회원 없음")})
	@DeleteMapping("/removePrivateMember")
	public ResponseEntity<String> boarddelete(@RequestParam String publicstudyroom_id,  @ApiIgnore Authentication authentication) throws Exception {	
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_id = userDetails.getUsername();
		try {	
			publicRoomService.deletePublicMember(publicstudyroom_id, user_id);
		}catch(Exception e ) {
			e.printStackTrace();
			System.out.println("해당 공개 스터디 참가자  제거 실패");
			return  ResponseEntity.status(500).body("해당 비공개 스터디 참가자 제거 "+FAIL);
		}
		logger.debug("해당 공개 스터디 참가자 명단에서 해당 user 삭제 성공");
		return ResponseEntity.status(200).body(user_id+"가 방에 나가서 공개 스터디 멤버에서 제거 함"+SUCCESS);
	}
}