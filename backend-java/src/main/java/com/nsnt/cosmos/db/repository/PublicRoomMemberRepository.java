package com.nsnt.cosmos.db.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.PublicMember;

/**
 * 공개 스터디 참가자 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface PublicRoomMemberRepository extends JpaRepository<PublicMember, Integer> { // 제네릭 안에 해당 엔티티, 엔티티 PK 자료형을 적어줌
	
	@Query(value="select * from public_member\r\n" + 
			"where publicstudyroom_id = :public_studyroom_id"        
        ,nativeQuery = true)
	
	List<PublicMember> findPublicMember(@Param("public_studyroom_id") String public_studyroom_id);
	
	@Transactional
	@Modifying
	@Query(value="insert into public_member(attendance, publicstudyroom_id, user_id) \r\n" + 
			"values (:attendance,:publicstudyroomId, :user_id)"        
            ,nativeQuery = true)
	public void insertPublicMember(@Param("attendance") boolean attendance, 
			  @Param("publicstudyroomId") String publicstudyroomId, @Param("user_id") String user_id);
	
	@Transactional
	@Modifying
	@Query(value="delete from public_member\r\n" + 
				"where publicstudyroom_id = :public_studyroom_id and user_id = :user_id"        
	        ,nativeQuery = true)
	void deletePublicMember(@Param("public_studyroom_id") String public_studyroom_id, @Param("user_id") String user_id);
}
