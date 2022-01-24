package com.nsnt.cosmos.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsnt.cosmos.api.request.StudyPostReq;
import com.nsnt.cosmos.api.service.StudyService;
import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.Study;
import com.nsnt.cosmos.db.repository.StudyRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * 스터디 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "스터디 API", tags = { "Study" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study")
public class StudyController {
	public static final Logger logger = LoggerFactory.getLogger(StudyController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	StudyService studyService;
	
	@Autowired
	private final StudyRepository studyRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	// 스터디 생성
	@PostMapping("/create")
	@ApiOperation(value = "스터디 생성", notes = "만들 스터디에 대한 정보를 입력하고 생성한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value = "스터디 정보", required = true) StudyPostReq registerInfo) {

		// 임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		studyService.createStudy(registerInfo);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	// 스터디 이름 중복 검사
	@GetMapping("/namecheck/{studyName}")
	@ApiOperation(value = "스터디 이름 중복 체크", notes = "스터디 생성 시 스터디 이름 중복 체크 검사(true면 중복, false면 중복아님)")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") 
					})
	public ResponseEntity<Boolean> checkStudyNameDuplicate(@PathVariable String studyName) {
		return ResponseEntity.ok(studyService.checkStudyNameDuplicate(studyName));
		
	}
	
	// 스터디 url 중복 검사
	@GetMapping("/urlcheck/{url}")
	@ApiOperation(value = "스터디 url 중복 체크", notes = "스터디 생성 시 스터디 url 중복 체크 검사(true면 중복, false면 중복아님)")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") 
					})
	public ResponseEntity<Boolean> checkUrlDuplicate(@PathVariable String url) {
		return ResponseEntity.ok(studyService.checkUrlDuplicate(url));
		
	}
	
	// 스터디 상세 조회
	@GetMapping("/search/{studyNo}")
	@ApiOperation(value = "스터디 상세 정보 표시", notes = "스터디에 대한 상세 정보 표시")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Study> findOneStudy(@PathVariable Long studyNo) {
		Study study = studyService.findByStudyNo(studyNo);
		return new ResponseEntity<Study>(study, HttpStatus.OK);
	}
	
	// 스터디 삭제
	@ApiOperation(value = "스터디 모집 해당 게시글 삭제", notes = "스터디 모집 해당 게시글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시글 삭제 성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "해당 회원 없음")})
	@DeleteMapping("/remove/{studyNo}")
	public ResponseEntity<String> userdelete(@PathVariable("studyNo") Long no) throws Exception {	
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
	@ApiOperation(value = "스터디 정보 수정", notes = "스터디 정보 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	@PutMapping("/update")
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
	
	
	
	// 스터디 수정
	@ApiOperation(value = "해당 멤버 스터디 조회", notes = "userId에 해당하는 멤버의 스터디들을 List로 반환")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	@PutMapping("/memberStudy/{userId}")
	public ResponseEntity<List<Study>> findMemberStudy(@PathVariable String userId) throws Exception {
		List<Study> study = studyService.findMemberStudy(userId);
		
		return new ResponseEntity<List<Study>>(study, HttpStatus.OK);
	}

}
