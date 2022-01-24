package com.nsnt.cosmos.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsnt.cosmos.api.request.UserUpdateDto;
import com.nsnt.cosmos.api.request.SaveBoardDto;
import com.nsnt.cosmos.api.request.SaveStudyMemberDto;
import com.nsnt.cosmos.api.request.UserRegisterPostReq;
import com.nsnt.cosmos.api.response.UserRes;
import com.nsnt.cosmos.api.service.BoardService;
import com.nsnt.cosmos.api.service.StudyMemberService;
import com.nsnt.cosmos.api.service.UserService;
import com.nsnt.cosmos.common.auth.SsafyUserDetails;
import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.Board;
import com.nsnt.cosmos.db.entity.Study;
import com.nsnt.cosmos.db.entity.StudyMember;
import com.nsnt.cosmos.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 스터디 멤버 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "스터디 멤버 관련 API", tags = { "StudyMember" })
@RestController
@RequestMapping("/api/studymember")
public class StudyMemberController {
	public static final Logger logger = LoggerFactory.getLogger(StudyMemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	StudyMemberService studyMeberService;

	@PostMapping("/register")
	@ApiOperation(value="스터디원 등록", notes="<strong>스터디원을 등록</strong>시켜줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity StudyMembergister(@RequestBody SaveStudyMemberDto saveStudyMemberDto)
	{
		StudyMember studymember;
		if(saveStudyMemberDto.isAuthority() == false && saveStudyMemberDto.isLeader() ==false) {
			return ResponseEntity.status(401).body("스터디 장이 아니거나 스터디장의 권한을 받지 않았습니다.");
		}
		try {
			System.out.println(saveStudyMemberDto.getUser_id()+" "+ saveStudyMemberDto.getStudy_no());
			studymember = studyMeberService.insertStudyMember(saveStudyMemberDto);
		}catch(Exception E) {
			E.printStackTrace();
			System.out.println("스터디원 등록 실패");
			return  ResponseEntity.status(500).body("디비 트랜잭션 오류로 인한 생성 실패");
		}
		System.out.println("잘 됨?"+ studymember.toString());
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	 /** 해당 스터디 멤버 회원들  조회 입니다. */
	@ApiOperation(value="해당 스터디 멤버 회원들  조회", notes="<strong>해당 스터디 멤버 회원들의 </strong> 정보를 출력 시켜줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	@GetMapping("/searchAll/{study_no}")
    public ResponseEntity<List<StudyMember>> findAllStudyMember(@PathVariable Long study_no){
        List<StudyMember> studymember = studyMeberService.findStudyMmeber(study_no);

        return new ResponseEntity<List<StudyMember>>(studymember,HttpStatus.OK);
    }

//	@GetMapping("/search/{study_no}")
//	
//	@ApiOperation(value ="해당 스터디 멤버 회원들  조회", notes ="해당 스터디 멤버 회원들  정보 출력")
//	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
//					@ApiResponse(code = 401, message = "인증 실패"),
//					@ApiResponse(code = 404, message = "사용자 없음"),
//					@ApiResponse(code = 500, message = "서버 오류") })
//	public ResponseEntity<StudyMember> findOneStudyMember(@PathVariable Long study_no) {
//		StudyMember studymember = studyMeberService.findByBoardId(study_no);
//		return new ResponseEntity<StudyMember>(studymember, HttpStatus.OK);
//	}
//
//	
//	@ApiOperation(value = "게시글 정보 수정", notes = "게시글 정보 수정")
//	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
//					@ApiResponse(code = 401, message = "인증 실패"),
//					@ApiResponse(code = 404, message = "사용자 없음"),
//					@ApiResponse(code = 500, message = "서버 오류") })
//	@PutMapping("/update")
//	public ResponseEntity<String> boardupdate(@RequestBody SaveBoardDto saveBoardDto) throws Exception {
//		Board board;
//		try {
//			board = boardService.findByBoardId(saveBoardDto.getBoard_no());
//		}catch(NoSuchElementException E) {
//			System.out.println("게시글 수정 실패");
//			return  ResponseEntity.status(500).body("해당 게시글이 없어서 게시글 수정 실패");
//		}
//		Board updateBoard = boardService.updateBoard(board, saveBoardDto);
//		System.out.println("업데이트 됨");
//		return new ResponseEntity<String>(SUCCESS+"\n"+updateBoard.toString(), HttpStatus.OK);
//	}
//	
//	// 스터디 모집 게시판 게시글 삭제
//	@ApiOperation(value = "스터디 모집 해당 게시글 삭제", notes = "스터디 모집 해당 게시글 삭제")
//	@ApiResponses({ @ApiResponse(code = 200, message = "게시글 삭제 성공"), 
//					@ApiResponse(code = 401, message = "인증 실패"),
//					@ApiResponse(code = 404, message = "사용자 없음"), 
//					@ApiResponse(code = 500, message = "해당 회원 없음")})
//	@DeleteMapping("/remove/{board_no}")
//	public ResponseEntity<String> boarddelete(@PathVariable("board_no") Long no) throws Exception {	
//		Board board;
//		try {
//			board = boardService.findByBoardId(no);
//			boardService.deleteBoard(board);
//		}catch(Exception e ) {
//			e.printStackTrace();
//			System.out.println("게시글 삭제 실패");
//			return  ResponseEntity.status(500).body("해당 게시글 없어서 삭제 "+FAIL);
//		}
//		logger.debug("스터디 모집 해당 게시글 삭제 성공");
//		return ResponseEntity.status(200).body(board.getBoardNo()+"번 해당 게시글 삭제"+SUCCESS);
//	}
}
