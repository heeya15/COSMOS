package com.nsnt.cosmos.db.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.api.response.UserHistoryWeekDtoRes;
import com.nsnt.cosmos.db.entity.UserHistoryWeek;

@Repository
public interface UserHistoryWeekRepository extends JpaRepository<UserHistoryWeek, Long>{

	@Query(value = "select user_id, sum(total_time) as total_time\r\n" + 
			"from userhistory_week\r\n" + 
			"where week = weekofyear(now()) - 1\r\n" + 
			"group by user_id\r\n" + 
			"order by total_time desc"
			, nativeQuery = true)
	List<Object[]> findAllWeekUserHistory(@Param("now") LocalDateTime now);
}
