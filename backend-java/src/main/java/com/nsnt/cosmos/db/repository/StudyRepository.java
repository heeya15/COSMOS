package com.nsnt.cosmos.db.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.db.entity.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {
	boolean existsByStudyName(String studyName);
	boolean existsByUrl(String url);
	
	@Query(value="select * "
			+ "from study s "
			+ "where s.study_no IN "
			+ "(select sm.study_no "
			+ "from user u join study_member sm "
			+ "on (sm.user_id= :userId )) "
//			+ "on (sm.user_id= ?1 )) " // @Param 어노테이션 x
			,nativeQuery = true)
	List<Study> findMemberStudy(@Param("userId") String userId);
//	List<Study> findMemberStudy(String user_id); // @Param 어노테이션 x
	
	@Transactional
	@Modifying
	@Query(value="update study s set number_of_member = (\r\n" + 
			"	select count(*)\r\n" + 
			"    from study_member sm\r\n" + 
			"    where sm.study_no = :studyNo\r\n"
			+ ")\r\n" + 
			"where s.study_no = :studyNo", nativeQuery = true)
	void updateNumberOfStudyMembers(@Param("studyNo") Long studyNo);

}
