package com.nsnt.cosmos.api.service;

import com.nsnt.cosmos.db.entity.StudyType;

/**
 *	스터디 분류 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface StudyTypeService {
	/** 스터디 분류 추가 함수 **/
	public void createStudyType(String studytype_name);
	
	/** 스터디 분류 삭제 함수 **/
	public void deleteStudyType(String studytype_name);
	
	/** 스터티 분류 수정하는 updateStudyType 입니다.*/
    public StudyType updateStudyType(StudyType studytype, String studytype_name);

    /**해당 스터디 분류 번호로 정보를 찾는 findByStudytypeNo 입니다.*/
	public StudyType findByStudytypeNo(int studytype_no);
}
