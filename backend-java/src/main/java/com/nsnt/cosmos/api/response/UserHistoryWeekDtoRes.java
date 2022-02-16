package com.nsnt.cosmos.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 유저 주별 랭킹 API ([GET] /api/history/searchAll/week) 요청에 대한 응답값 정의.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoryWeekDtoRes {
	Object user_id;
	Object total_time;

	
}