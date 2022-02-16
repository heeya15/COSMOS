package com.nsnt.cosmos.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.UserHistoryDay;

/**
 * 강퇴 유저 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface DayRepository extends JpaRepository<UserHistoryDay, Integer> { // 제네릭 안에 해당 엔티티, 엔티티 PK 자료형을 적어줌
	
	@Query(value="select count(*) from banned_user where user_id = :user_id and publicstudyroom_id = :publicstudyroom_id\r\n"
			, nativeQuery = true)
	int findByUserId(@Param("publicstudyroom_id") String publicstudyroom_id, @Param("user_id") String user_id);
//	
//	@Query(value="", nativeQuery = true)
//	void insertUserHistoryDay(@)
}
