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
public interface UserHistoryDayRepository extends JpaRepository<UserHistoryDay, Long>{

	@Query(value = "select * \r\n" + 
			"from userhistory_day ud \r\n" + 
			"where day = dayofyear(:now) - 1 \r\n" + 
			"order by total_time desc"
			, nativeQuery = true)
	List<UserHistoryDay> findAllDayUserHistory(@Param("now") LocalDateTime now);
}
