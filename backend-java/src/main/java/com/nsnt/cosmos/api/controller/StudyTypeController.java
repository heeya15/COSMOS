package com.nsnt.cosmos.api.controller;

import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nsnt.cosmos.api.service.StudyTypeService;
import com.nsnt.cosmos.db.entity.StudyType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 스터디 분류  관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "스터디 분류 API", tags = { "StudyType" })
@RestController
@RequestMapping("/api/studytype")
public class StudyTypeController {
	public static final Logger logger = LoggerFactory.getLogger(StudyTypeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	StudyTypeService studyTypeService;
	
	@PostMapping("/register")
	@ApiOperation(value="스터디 분류 등록 (param)", notes="<strong>스터디 분류 등록 </strong>시켜줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "not found"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<String> StudyTyperegister(@RequestParam String studytype_name)
	{	
		try {	
			 studyTypeService.createStudyType(studytype_name);
		}catch(Exception e ) {
			e.printStackTrace();
			System.out.println("스터디 분류 추가 실패");
			return  ResponseEntity.status(500).body("스터디 분류 추가  "+FAIL);
		}
		logger.debug("스터디 분류 추가 성공");
		return ResponseEntity.status(200).body(studytype_name +" 라는 스터디 분류 분류 등록 함 "+SUCCESS);	
	}
	
	
	@ApiOperation(value = "스터디 분류 이름 수정(param)", notes = "스터디 분류 이름 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	@PutMapping("/update")
	public ResponseEntity<String> updateStudyType(@RequestParam int studytype_no, @RequestParam String studytype_name ) throws Exception {
		StudyType studytype;
		
		try {
			studytype = studyTypeService.findByStudytypeNo(studytype_no);
		}catch(NoSuchElementException E) {
			System.out.println("스터디 분류 이름 수정 수정 실패");
			return  ResponseEntity.status(500).body("해당 스터디 분류 번호가 없어서 수정 실패");
		}
		StudyType updateStudyType = studyTypeService.updateStudyType(studytype, studytype_name);
		System.out.println("업데이트 됨");
		return new ResponseEntity<String>(SUCCESS+"\n"+ updateStudyType.toString(), HttpStatus.OK);
	}
	

	/** 스터디 분류 삭제 **/
	@ApiOperation(value = "스터디 분류 삭제(param)", notes = "스터디 분류 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "스터디 분류 삭제 성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "해당 회원 없음")})
	@DeleteMapping("/remove/studyType")
	public ResponseEntity<String> removeStudyType(@RequestParam String studytype_name) throws Exception {	
		try {	
			studyTypeService.deleteStudyType(studytype_name);
		}catch(Exception e ) {
			e.printStackTrace();
			System.out.println("스터디 분류 삭제실패");
			return  ResponseEntity.status(500).body("스터디 분류 삭제  "+FAIL);
		}
		logger.debug("스터디 분류 삭제 성공");
		return ResponseEntity.status(200).body(studytype_name +" 라는 스터디  분류 삭제 함 "+SUCCESS);
	}
}