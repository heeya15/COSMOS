package com.nsnt.cosmos.api.response;
/**
 * 스터디 멤버 신청 유저 정보 조회 API ([GET] /searchAll/{board_no}) 요청에 대한 응답값 정의.
 */
public interface ApplyMemberDtoRes {
	String getApplymember_no();
	Long getStudy_no();
	String getUser_id();
	String getUser_name();
}