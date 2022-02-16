package com.nsnt.cosmos.api.service;

import java.util.List;

import com.nsnt.cosmos.api.response.UserHistoryWeekDtoRes;
import com.nsnt.cosmos.db.entity.UserHistory;
import com.nsnt.cosmos.db.entity.UserHistoryDay;
import com.nsnt.cosmos.db.entity.UserHistoryMonth;

/**
 * 히스토리 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserHistoryService {
	/** 비공개 방 입장/퇴장 시간 업데이트 */
	UserHistory setPrivateStartTime(String user_id);
	void setPrivateUserFinishTime(String user_id, Long userhistory_no);
	
	/** 공개 방 유저별 스탑워치 시작/종료 시간 업데이트 */
	UserHistory setPublicUserStartTime(String start_time, String user_id);
	void setPublicUserFinishTime(String finish_time, Long userhistory_no, String user_id);
	
	/** 일별 랭킹 정보 조회 */
	List<UserHistoryDay> getDailyUserHistory();
	
	/** 주별 랭킹 정보 조회 */
	List<UserHistoryWeekDtoRes> getWeeklyUserHistory();
	
	/** 월별 랭킹 정보 조회 */
	List<UserHistoryMonth> getMonthlyUserHistory();
	
	/** 유저별 총 누적 시간 조회 */
	int getUserTotalTime(String user_id);
}
