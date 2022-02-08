package com.nsnt.cosmos.db.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nsnt.cosmos.db.entity.PublicStudyRoom;

/**
 * 공개 스터디룸 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface PublicStudyRoomRepository extends JpaRepository<PublicStudyRoom, String> { // 제네릭 안에 해당 엔티티, 엔티티 PK 자료형을 적어줌
	boolean existsByUrl(String url);
	
	@Transactional
	@Modifying
	@Query(value="delete from public_study_room\r\n" + 
			     "where publicstudyroom_id =:publicstudyroom_id"        
	        ,nativeQuery = true)
	void deletePublicRoom(@Param("publicstudyroom_id") String public_studyroom_id);
}
