package com.nsnt.cosmos.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public UserHistory setUserStartTime(String user_id) {
		UserHistory userhistory = new UserHistory();
		LocalDateTime now = LocalDateTime.now();
		User user = new User();
		user.setUserId(user_id);
		userhistory.setDate(now);
		userhistory.setUserStartTime(now);
		userhistory.setUserFinishTime(now);
		userhistory.setUser(user);
		
		return userHistoryRepository.save(userhistory);	
	}
	
	public void setUserFinishTime(String user_id, String userhistory_no) {
		LocalDateTime now = LocalDateTime.now();
		userHistoryRepository.setFinishTime(user_id, now, userhistory_no);
	}
}
