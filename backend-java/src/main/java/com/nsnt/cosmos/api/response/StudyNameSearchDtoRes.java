package com.nsnt.cosmos.api.response;

import io.swagger.annotations.ApiModel;

/**
 * 스터디 이름 조회 API ([POST] /api/studymember/search/{study_no}) 요청에 대한 응답값 정의.
 */

@ApiModel("StudyNameSearchDtoRes")
public interface StudyNameSearchDtoRes{
	String getStudy_name(); // get을 써줘야 한다.
	int getStudy_no();
}
