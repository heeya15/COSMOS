package com.nsnt.cosmos.db.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.PrivateMember;

/**
 * 비공개 스터디 참가자 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface PrivateRoomMemberRepository extends JpaRepository<PrivateMember, Integer> { // 제네릭 안에 해당 엔티티, 엔티티 PK 자료형을 적어줌
	@Query(value="select * from private_member\r\n" + 
				"where privatestudyroom_id = :privatestudyroom_id"        
            ,nativeQuery = true)
    List<PrivateMember> findPirvateMember(@Param("privatestudyroom_id") String privatestudyroom_id);
	@Transactional
	@Modifying
	@Query(value="delete from private_member\r\n" + 
				"where privatestudyroom_id = :privatestudyroom_id and user_id = :user_id"        
            ,nativeQuery = true)
	void deletePirvateMember(@Param("privatestudyroom_id") String privatestudyroom_id, @Param("user_id") String user_id);
}
