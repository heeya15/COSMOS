package com.nsnt.cosmos.api.service;

import java.util.List;

import com.nsnt.cosmos.db.entity.UserHistory;
import com.nsnt.cosmos.db.entity.UserHistoryDay;

/**
 * 히스토리 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserHistoryService {
	UserHistory setPrivateStartTime(String user_id);
	void setPrivateUserFinishTime(String user_id, Long userhistory_no);
	
	UserHistory setPublicUserStartTime(String start_time, String user_id);
	void setPublicUserFinishTime(String finish_time, Long userhistory_no, String user_id);
	
	/** 일별 랭킹 정보 조회 */
	List<UserHistoryDay> getDailyUserHistory();
}
