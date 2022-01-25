package com.nsnt.cosmos.api.controller;

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

import com.nsnt.cosmos.api.request.StudyManagePostReq;
import com.nsnt.cosmos.api.service.StudyManageService;
import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.StudyManage;
import com.nsnt.cosmos.db.repository.StudyManageRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * 스터디 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "스터디 API", tags = { "StudyManage" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/studyManage")
public class StudyManageController {
	public static final Logger logger = LoggerFactory.getLogger(StudyManageController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	StudyManageService studyManageService;
	
	@PersistenceContext
	private EntityManager entityManager;

	// 스터디 공지사항 생성
	@PostMapping("/register")
	@ApiOperation(value = "스터디 공지사항 생성", notes = "해당 스터디의 공지사항 내용을 입력하고 생성한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value = "스터디 정보", required = true) StudyManagePostReq registerInfo) {

		// 임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		studyManageService.createStudyManage(registerInfo);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	// 스터디 공지사항 상세 조회
	@GetMapping("/search/{studyNo}")
	@ApiOperation(value = "스터디 공지사항 상세 정보 표시", notes = "스터디에 공지사항 대한 상세 정보 표시")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<StudyManage> findOneStudy(@PathVariable Long studyNo) {
		StudyManage studyManage = studyManageService.findByStudyNo(studyNo);
		
		return new ResponseEntity<StudyManage>(studyManage, HttpStatus.OK);
	}
	
	// 스터디 공지사항 수정
	@ApiOperation(value = "스터디 공지사항 정보 수정", notes = "스터디 공지사항 정보 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody StudyManagePostReq studyManageDto) throws Exception {
		StudyManage studyManage;
		try {
			studyManage = studyManageService.findByStudyNo(studyManageDto.getStudyNo());
		}catch(NoSuchElementException E) {
			System.out.println("공지사항 수정 실패");
			return  ResponseEntity.status(500).body("해당 공지사항이 없어서 공지사항 수정 실패");
		}
		studyManageService.updateStudyManage(studyManage, studyManageDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	// 스터디 공지사항 삭제
	@ApiOperation(value = "스터디 공지사항 삭제", notes = "스터디 공지사항 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시글 삭제 성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "해당 회원 없음")})
	@DeleteMapping("/remove/{studyNo}")
	public ResponseEntity<String> userdelete(@PathVariable("studyNo") Long no) throws Exception {	
		StudyManage studyManage;
		try {
			studyManage = studyManageService.findByStudyNo(no);
			studyManageService.deleteStudyManage(studyManage);
			logger.debug("스터디 삭제 성공");
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResponseEntity.status(500).body("스터디 삭제 실패 "+FAIL);
		}
		return ResponseEntity.status(200).body("스터디 삭제 "+SUCCESS);
	}

}
