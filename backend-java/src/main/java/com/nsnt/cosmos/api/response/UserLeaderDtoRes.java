package com.nsnt.cosmos.api.response;

import io.swagger.annotations.ApiModel;


/**
 * 유저 로그인 API ([POST] /api/v1/leader) 요청에 대한 응답값 정의.
 */

@ApiModel("UserLeaderDtoRes")
public interface UserLeaderDtoRes {
	boolean getLeader ();   // 스터디장 여부
	boolean getAuthority(); // 권한 여부
}
