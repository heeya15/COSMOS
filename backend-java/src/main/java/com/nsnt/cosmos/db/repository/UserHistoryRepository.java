package com.nsnt.cosmos.db.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.UserHistory;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long>{
	@Transactional
	@Modifying
	@Query(value = "update userhistory set user_finish_time = :now \r\n" + 
			"where user_id = :user_id and userhistory_no = :userhistory_no "
			, nativeQuery = true)
	void setFinishTime(@Param("user_id") String user_id, @Param("now") LocalDateTime now, @Param("userhistory_no") String userhistory_no);
}
