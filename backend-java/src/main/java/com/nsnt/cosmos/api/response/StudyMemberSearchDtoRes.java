package com.nsnt.cosmos.api.response;

import io.swagger.annotations.ApiModel;

/**
 * 스터디 멤버 회원 조회 API ([POST] /api/studymember/search/{study_no}) 요청에 대한 응답값 정의.
 */

@ApiModel("StudyMemberSearchDtoRes")
public interface StudyMemberSearchDtoRes{
	String getUser_name(); // get을 써줘야 한다.
	String getUser_email();
	boolean getAttendance ();
	
	int getStudytime(); // 공부 시간
	int getScore(); // 숫자
}
