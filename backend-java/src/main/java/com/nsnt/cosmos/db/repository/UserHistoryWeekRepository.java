package com.nsnt.cosmos.db.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.UserHistoryWeek;

@Repository
public interface UserHistoryWeekRepository extends JpaRepository<UserHistoryWeek, Long>{

	@Query(value = "select *\r\n" + 
			"from userhistory_week\r\n" + 
			"where week = weekofyear(:now) - 1 \r\n" + 
			"order by total_time desc"
			, nativeQuery = true)
	List<UserHistoryWeek> findAllWeekUserHistory(@Param("now") LocalDateTime now);
}
