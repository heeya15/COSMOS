package com.nsnt.cosmos.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nsnt.cosmos.db.entity.PrivateStudyRoom;

/**
 * 비공개 스터디룸 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface PrivateStudyRoomRepository extends JpaRepository<PrivateStudyRoom, String> { // 제네릭 안에 해당 엔티티, 엔티티 PK 자료형을 적어줌
	
}
