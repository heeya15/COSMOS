package com.nsnt.cosmos.api.service;

import com.nsnt.cosmos.api.request.StudyManagePostReq;
import com.nsnt.cosmos.db.entity.StudyManage;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface StudyManageService {
	StudyManage createStudyManage(StudyManagePostReq studyManageRegisterInfo);
	public StudyManage findByStudyNo(Long studyNo);
	public StudyManage updateStudyManage(StudyManage studyManage, StudyManagePostReq studyManageDto);
	public void deleteStudyManage(StudyManage studyManage);
}
