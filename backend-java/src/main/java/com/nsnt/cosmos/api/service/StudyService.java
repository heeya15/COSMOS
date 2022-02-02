package com.nsnt.cosmos.api.service;

import java.util.List;

import com.nsnt.cosmos.api.request.StudyPostReq;
import com.nsnt.cosmos.db.entity.Study;
import com.nsnt.cosmos.db.entity.StudyType;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface StudyService {
	Study createStudy(StudyPostReq studyRegisterInfo);
	boolean checkStudyNameDuplicate(String studyName);
	boolean checkUrlDuplicate(String url);
	public Study findByStudyNo(Long studyNo);
	public void deleteStudy(Study study);
	public Study updateStudy(Study study, StudyPostReq studyUpdateInfo);
	public List<Study> findMemberStudy(String userId);
	List<StudyType> findAllStudyType();
	public void updateNumberOfStudyMember(Long studyNo);
}
