package com.nsnt.cosmos.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.ApplyMember;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ApplyMemberRepository extends JpaRepository<ApplyMember, Integer> { // 제네릭 안에 해당 엔티티, 엔티티 PK 자료형을 적어줌
	// 해당 스터디 멤버를 신청한 유저들을 조회
	@Query(value = "select * \r\n"
			+ "from apply_member am join User u \r\n"
			+ "on am.user_id = u.user_id \r\n"
			+ "where study_no = :study_no "
			, nativeQuery = true)
	List<ApplyMember> findAllByStudyNo(@Param("study_no") Long study_no);
}
