package com.nsnt.cosmos.api.response;

import io.swagger.annotations.ApiModel;

/**
 * 스터디 생성 후 해당 스터디 생성한 사람의 study_no 조회 API ([GET] /api/studymember/resent/search) 요청에 대한 응답값 정의.
 */

@ApiModel("RecentStudyNorSearchDtoRes")
public interface RecentStudyNorSearchDtoRes{
	Long getStudy_no();
}
