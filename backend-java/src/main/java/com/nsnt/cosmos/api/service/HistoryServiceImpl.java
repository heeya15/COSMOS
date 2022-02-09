package com.nsnt.cosmos.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsnt.cosmos.api.request.SaveUserHistoryDto;
import com.nsnt.cosmos.db.entity.User;
import com.nsnt.cosmos.db.entity.UserHistory;
import com.nsnt.cosmos.db.repository.UserHistoryRepository;

/**
 *	히스토리 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("HistoryServiceImpl")
public class HistoryServiceImpl implements HistoryService {
	@Autowired
	UserHistoryRepository userHistoryRepository;

	@Override
	public UserHistory insertUserHistory(SaveUserHistoryDto userhisroyDto, String user_id) {
		UserHistory userhistory = new UserHistory();
		LocalDateTime now = LocalDateTime.now();
		User user = new User();
		user.setUserId(user_id);
		if(userhisroyDto.getState().equals("start")) {
			userhistory.setDate(now);
			userhistory.setUserStartTime(now);
			userhistory.setUser(user);
		}
		if (userhisroyDto.getState().equals("finish")){
			userhistory.setDate(now);
			userhistory.setUserFinishTime(now);
			userhistory.setUser(user);
		}
//		LocalDateTime start = userhisroyDto.getUserStartTime();
//		LocalDateTime finish = userhisroyDto.getUserFinishTime();
//		if(finish == null) { // 종료시간이 입력이 되지 않았다면
//			userhistory.setDate(today);
//			userhistory.setUserStartTime(start);
//			userhistory.setUser(user);
//		}else { // 시작 시간이 입력이 되지 않았다면
//			userhistory.setDate(today);
//			userhistory.setUserFinishTime(finish);
//			userhistory.setUser(user);
//		}
		
		return userHistoryRepository.save(userhistory);
	}
}
