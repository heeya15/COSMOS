package com.nsnt.cosmos.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nsnt.cosmos.db.entity.StudyMember;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Long>{
	@Query(value="SELECT *\r\n" + 
			"	  FROM (SELECT *\r\n" + 
			"	  		FROM study_member\r\n" + 
			"      where study_no = :study_no) sm join user u ON (sm.user_id = u.user_id) "        
            ,nativeQuery = true)
    List<StudyMember> findStudyMember(@Param("study_no") Long study_no);
}
