package com.nsnt.cosmos.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.api.response.StudyMemberSearchDtoRes;
import com.nsnt.cosmos.db.entity.StudyMember;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Long>{
	@Query(value="SELECT u.user_name as user_name, u.user_email as user_email,\r\n" + 
			"	   sm.attendance as attendance, sm.studytime as studytime, sm.score as score\r\n" + 
			"FROM (SELECT user_id, studymember_no, attendance, studytime, score\r\n" + 
			"	  FROM study_member\r\n" + 
			"      where study_no = :study_no) sm join user u ON (sm.user_id = u.user_id) "        
            ,nativeQuery = true)
    List<StudyMemberSearchDtoRes> findStudyMember(@Param("study_no") Long study_no);
}
