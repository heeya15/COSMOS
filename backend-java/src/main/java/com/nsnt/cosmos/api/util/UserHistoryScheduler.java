package com.nsnt.cosmos.api.util;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nsnt.cosmos.db.repository.UserHistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserHistoryScheduler {
	@Autowired
	UserHistoryRepository userhistoryrepository;
	
	@Scheduled(cron = "00 59 11 * * *")
	@Transactional
	public void setDailyUserSchedule() {
		System.out.println(">>>>>>>>>>>>> Running Scheduler!! " + LocalDateTime.now());
		userhistoryrepository.updateUserDailyHistory(LocalDateTime.now());
	}
}
