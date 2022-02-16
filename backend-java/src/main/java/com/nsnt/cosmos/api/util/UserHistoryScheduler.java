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
	
//		초 분 시 일 월 년
//	* : 모든 값. ( 매분, 매시, 매일, 매주, 매월, 매요일, 매년 : * * * * * * * ) 
//	? : 특정한 값 없음. 날짜와 요일에만 사용하며, 어떤 값이든 상관없음을 뜻함.
//	- : 범위. ( 월요일에서 수요일까지 MON-WED )
//	, : 특별한 값의 추가 ( 월,수,금 MON,WED,FRI )
//	/ : 증분 ( 0분부터 매 5분 마다 0/5 )
//	L : 마지막 ( 날짜와 요일에만 사용하며 매주마지막 요일(토요일) , 매월 말일을 뜻합니다. )
//	W: 가장 가까운 평일 ( 가령 15W라고 하면 15일에서 가장 가까운 평일을 뜻합니다. )
//	# : 몇째주의 무슨 요일을 표현할 때 사용. 
	
	// 매일 오후 11시 59분 그 날의 사용자 공부 시간 히스토리 uerhistory_day 테이블로 insert
	@Scheduled(cron = "00 57 23 * * *")
	@Transactional
	public void setDailyUserSchedule() {
		System.out.println(">>>>>>>>>>>>> [Day] Running Scheduler!! " + LocalDateTime.now());
		userhistoryrepository.updateUserDailyHistory(LocalDateTime.now());
	}
	
	// 매주 일요일 오후 11시 58분 그 주의 사용자 공부 시간 히스토리 usershitory_week 테이블로 insert
	@Scheduled(cron = "00 58 11 * * 1")
	@Transactional
	public void setWeeklyUserSchedule() {
		System.out.println(">>>>>>>>>>>>> [Week] Running Scheduler!! " + LocalDateTime.now());
		userhistoryrepository.updateUserWeeklyHistory(LocalDateTime.now());
	}
	
	// 매월 마지막 날 오후 11시 57분 해당 월의 사용자 공부 시간 히스토리 usershitory_month 테이블로 insert
	@Scheduled(cron = "00 59 11 L * *")
	@Transactional
	public void setMonthlyUserSchedule() {
		System.out.println(">>>>>>>>>>>>> [Month] Running Scheduler!! " + LocalDateTime.now());
		userhistoryrepository.updateUserMonthlyHistory(LocalDateTime.now());
	}
}
