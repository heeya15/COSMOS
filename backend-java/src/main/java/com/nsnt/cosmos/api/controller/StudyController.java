package com.nsnt.cosmos.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

import com.nsnt.cosmos.api.request.StudyPostReq;
import com.nsnt.cosmos.api.response.ApplyMemberDtoRes;
import com.nsnt.cosmos.api.service.ApplyMemberService;
import com.nsnt.cosmos.api.service.StudyService;
import com.nsnt.cosmos.api.service.UserService;
import com.nsnt.cosmos.common.auth.SsafyUserDetails;
import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.ApplyMember;
import com.nsnt.cosmos.db.entity.Study;
import com.nsnt.cosmos.db.entity.StudyType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 스터디 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "스터디 API", tags = { "Study" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study")
public class StudyController {
	public static final Logger logger = LoggerFactory.getLogger(StudyController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	UserService userService;
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	ApplyMemberService applyMemberService;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	/** 스터디 관련 부분 **/
	// 스터디 생성
	@PostMapping("/register")
	@ApiOperation(value = "스터디 생성 (token)", notes = "만들 스터디에 대한 정보를 입력하고 생성한다. user_id는 빈 괄호(\"\")를 입력하여 주세요.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value = "스터디 정보", required = true) StudyPostReq registerInfo, @ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String user_id = userDetails.getUsername();
		
		registerInfo.setUserId(user_id);
		
		// 임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		studyService.createStudy(registerInfo);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	// 스터디 이름 중복 검사
	@GetMapping("/namecheck/{study_name}")
	@ApiOperation(value = "스터디 이름 중복 체크", notes = "스터디 생성 시 스터디 이름 중복 체크 검사(true면 중복, false면 중복아님)")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"),
					@ApiResponse(code = 500, message = "서버 오류") 
					})
	public ResponseEntity<Boolean> checkStudyNameDuplicate(@PathVariable("study_name") String studyName) {
		return ResponseEntity.ok(studyService.checkStudyNameDuplicate(studyName));
		
	}
	
	// 스터디 방 생성시 url 중복 검사
	@GetMapping("/urlcheck")
	@ApiOperation(value = "스터디 방 생성시 url 중복 체크(param)", notes = "스터디 방 생성 시 스터디 url 중복 체크 검사(true면 중복, false면 중복아님)")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "url 없음"),
					@ApiResponse(code = 500, message = "서버 오류") 
					})
	public ResponseEntity<Boolean> checkUrlDuplicate(@RequestParam String url) {
		System.out.println("들어옴?");
		return ResponseEntity.ok(studyService.checkUrlDuplicate(url));
	}
	
	// 스터디 상세 조회
	@GetMapping("/search/{study_no}")
	@ApiOperation(value = "스터디 상세 정보 표시", notes = "스터디에 대한 상세 정보 표시")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Study> findOneStudy(@PathVariable("study_no") Long studyNo) {
		Study study = studyService.findByStudyNo(studyNo);
		return new ResponseEntity<Study>(study, HttpStatus.OK);
	}
	
	// 스터디 삭제
	@ApiOperation(value = "스터디 삭제", notes = "스터디 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시글 삭제 성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "해당 회원 없음")})
	@DeleteMapping("/remove/{study_no}")
	public ResponseEntity<String> userdelete(@PathVariable("study_no") Long no) throws Exception {	
		Study study;
		try {
			study = studyService.findByStudyNo(no);
			studyService.deleteStudy(study);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResponseEntity.status(500).body("스터디 삭제 실패 "+FAIL);
		}
		logger.debug("스터디 삭제 성공");
		return ResponseEntity.status(200).body("스터디 삭제 "+SUCCESS);
	}
	
	// 스터디 수정
	@PutMapping("/update")
	@ApiOperation(value = "스터디 정보 수정", notes = "스터디 정보 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> update(@RequestBody StudyPostReq studyDto) throws Exception {
		Study study;
		try {
			study = studyService.findByStudyNo(studyDto.getStudyNo());
		}catch(NoSuchElementException E) {
			System.out.println("게시글 수정 실패");
			return  ResponseEntity.status(500).body("해당 게시글이 없어서 게시글 수정 실패");
		}
		Study updateStudy = studyService.updateStudy(study, studyDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	// 해당 멤버의 스터디 조회
	@GetMapping("/memberStudy")
	@ApiOperation(value = "해당 멤버 스터디 조회 (token)", notes = "userId에 해당하는 멤버의 스터디들을 List로 반환")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<Study>> findMemberStudy(@ApiIgnore Authentication authentication) throws Exception {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String userId = userDetails.getUsername();	// 현재 로그인 중인 유저 아이디 받아오기
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> userId : " + userId);
		List<Study> study = studyService.findMemberStudy(userId);

		return new ResponseEntity<List<Study>>(study, HttpStatus.OK);
	}
	
	/** 스터디 신청 멤버관련 기능 **/
	
	// 스터디 멤버 신청
	@PostMapping("/applyMember/register/{study_no}")
	@ApiOperation(value = "스터디 멤버 신청 추가 (token)", notes = "스터디를 신청한 멤버를 추가한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "유저 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> registerApplyMember(@ApiIgnore Authentication authentication
												,@PathVariable Long study_no) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저
		 * 식별. 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access
		 * Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>> userDetails : {}", userDetails);
		String userId = userDetails.getUsername();	// 현재 로그인 중인 유저 아이디 받아오기
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>> userId : {}", userId);

		ApplyMember applymember = applyMemberService.createMember(study_no, userId);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	// 스터디 멤버 신청 유저 목록
	@GetMapping("/applyMember/searchAll/{study_no}")
	@ApiOperation(value="스터디 멤버 신청 유저 조회", notes="<strong>해당 스터디 멤버를 신청한 유저를 전체 조회를</strong>시켜줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "유저 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<List<ApplyMemberDtoRes>> findAllApplyMember(@PathVariable Long study_no){
		List<ApplyMemberDtoRes> applyMember = applyMemberService.findAllByStudyNo(study_no);
		return new ResponseEntity<List<ApplyMemberDtoRes>>(applyMember, HttpStatus.OK);
	}
	
	// 스터디 멤버 신청 유저 삭제
	@DeleteMapping("/applyMember/remove/{applymember_no}")
	@ApiOperation(value = "스터디 신청 유저 중 해당 유저 삭제", notes = "스터디 신청 유저 중 해당 유저 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "유저 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<String> deleteApplyMember(@PathVariable("applymember_no") int applymember_no) throws Exception {	
		ApplyMember applyMember;
		try {
			applyMember = applyMemberService.findByApplyMemberNo(applymember_no);
			applyMemberService.deleteApplyMember(applyMember);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResponseEntity.status(500).body(FAIL);
		}
		return ResponseEntity.status(200).body(SUCCESS);
	}
	
	// 알고리즘 분류 전체 조회
	@GetMapping("/studyType")
	@ApiOperation(value="알고리즘 분류 전체 조회", notes="알고리즘 분류 목록을 전체 불러옴")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "알고리즘 타입 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<List<StudyType>> findAllBoard(){
        List<StudyType> studyType = studyService.findAllStudyType();

        return new ResponseEntity<List<StudyType>>(studyType,HttpStatus.OK);
    }
	
}