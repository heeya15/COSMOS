package com.nsnt.cosmos.db.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.UserHistory;
import com.nsnt.cosmos.db.entity.UserHistoryDay;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long>{

	@Transactional
	@Modifying
	@Query(value = "update userhistory set user_finish_time = :now \r\n" + 
			"where user_id = :user_id and userhistory_no = :userhistory_no "
			, nativeQuery = true)
	void setFinishTime(@Param("user_id") String user_id, @Param("now") LocalDateTime now, @Param("userhistory_no") long userhistory_no);
	
	@Transactional
	@Modifying
	@Query(value ="insert into userhistory_day (day, user_id, total_time, day_date)\r\n" + 
			"select dayofyear(user_start_time) as day_no, user_id, sum(TIMESTAMPDIFF(second, user_start_time, IFNULL(user_finish_time, now()))) as total_time, date(history_date) as today\r\n" + 
			"from userhistory\r\n" + 
			"group by day_no, user_id, today\r\n" + 
			"having day_no = dayofyear(:now)"
			, nativeQuery = true)
	void updateUserDailyHistory(@Param("now") LocalDateTime now);
	
	@Transactional
	@Modifying
	@Query(value = "insert into userhistory_week (week, week_date, user_id, total_time)\r\n" + 
			"select weekofyear(day_date) as week_no, day_date, user_id, sum(total_time) as time\r\n" + 
			"from userhistory_day\r\n" + 
			"group by week_no, day_date, user_id\r\n" + 
			"having week_no = weekofyear(:now)"
			, nativeQuery = true)
	void updateUserWeeklyHistory(@Param("now") LocalDateTime now);
	
	@Transactional
	@Modifying
	@Query(value = "insert into userhistory_month (month, user_id, total_time)\r\n" + 
			"select month(week_date) as month_no, user_id, sum(total_time) as time\r\n" + 
			"from userhistory_week\r\n" + 
			"group by month_no, user_id\r\n" + 
			"having month_no = month(:now) "
			, nativeQuery = true)
	void updateUserMonthlyHistory(@Param("now") LocalDateTime now); 

}
