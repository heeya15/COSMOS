package com.nsnt.cosmos.db.repository;

import com.nsnt.cosmos.api.response.StudyMemberSearchDtoRes;
import com.nsnt.cosmos.db.entity.QStudyMember;
import com.nsnt.cosmos.db.entity.QUser;
import com.nsnt.cosmos.db.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class StudyMemberRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    private StudyMemberRepository studyRepository;
    QStudyMember qstudymember =QStudyMember.studyMember;
    QUser quser = QUser.user;
//    @Query(value="SELECT u.user_name, u.user_email, sm.attendance, sm.studytime, sm.score\r\n" + 
//			"FROM (SELECT user_id, studymember_no,attendance, studytime, score\r\n" + 
//			"	  FROM study_member\r\n" + 
//			"      where study_no = :study_no) sm join user u ON (sm.user_id = u.user_id)"     
			
//    select u.user_name, u.user_email, sm.attendance, sm.score, sm.studytime
//    from study_member sm join user u
//    using (user_id)
//    where study_no = 1;	
    
    public List<StudyMemberSearchDtoRes> findstudyMembe(Long study_no) {
    	List<StudyMemberSearchDtoRes> result = (List<StudyMemberSearchDtoRes>) jpaQueryFactory.select(quser.userName, quser.userEmail, qstudymember.attendance, qstudymember.studytime, qstudymember.score)
    	.from(quser).join(qstudymember).on(quser.userId.eq(qstudymember.user.userId)).
    	where(qstudymember.study.studyNo.eq(study_no)).fetchAll();
        return result;
    }
   
}
