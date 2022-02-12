package com.nsnt.cosmos.db.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.UserHistoryMonth;
import com.nsnt.cosmos.db.entity.UserHistoryWeek;

@Repository
public interface UserHistoryMonthRepository extends JpaRepository<UserHistoryMonth, Long>{

	@Query(value = "select *\r\n" + 
			"from userhistory_month\r\n" + 
			"where month = month(:now) \r\n" + 
			"order by total_time desc"
			, nativeQuery = true)
	List<UserHistoryMonth> findAllMonthUserHistory(@Param("now") LocalDateTime now);
}
