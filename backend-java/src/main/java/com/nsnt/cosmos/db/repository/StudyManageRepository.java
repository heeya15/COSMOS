package com.nsnt.cosmos.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.StudyManage;

// Optional<T>는 null이 올수 있는 값을 감싸는 Wrapper클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다
// Repository의 정확한 사용은 DAO를 위해 사용하는 어노테이션인데 JpaRepository는 JPA의 구현체라고 할 수 있다.
// 기본적인 CRUD가 정의되어 있는 JpaRepository를 구현하였고 사용자들은 그 구현체를 상속하여 사용하면 되는 것이다.
/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface StudyManageRepository extends JpaRepository<StudyManage, Long> {

	@Query(value ="select *\r\n" + 
			"from study_manage\r\n" + 
			"where study_no = :studyNo", nativeQuery = true)
	StudyManage findStudyManage(@Param("studyNo") Long studyNo);

	@Query(value = "delete\r\n" + 
			"from study_manage\r\n" + 
			"where study_no = :studyNo", nativeQuery = true)
	void removeStudyManage(@Param("studyNo") Long no);
  
}