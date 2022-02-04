package com.nsnt.cosmos.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsnt.cosmos.api.request.StudyManagePostReq;
import com.nsnt.cosmos.db.entity.Study;
import com.nsnt.cosmos.db.entity.StudyManage;
import com.nsnt.cosmos.db.entity.StudyManageId;
import com.nsnt.cosmos.db.repository.StudyManageRepository;

/**
 *	스터디 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("studyManageService")
public class StudyManageServiceImpl implements StudyManageService {

	@Autowired
	StudyManageRepository studyManageRepository;
	
	/** 스터디 공지사항을 생성하는 createStudyManage 메소드입니다. */
	@Override
	public StudyManage createStudyManage(StudyManagePostReq studyManageRegisterInfo) {
		long studyManageNo = 1;
		StudyManage studyManage = new StudyManage();
		
		StudyManageId studyManageId = new StudyManageId();
		studyManageId.setStudyManageNo(studyManageNo);
		
		Study study = new Study();
		study.setStudyNo(studyManageRegisterInfo.getStudyNo());
		studyManageId.setStudyNo(study);
		
		studyManage.setStudymanageId(studyManageId);
		studyManage.setStudymanageNotice(studyManageRegisterInfo.getStudymanageNotice());
		studyManage.setCreatedAt(LocalDateTime.now());
		
		return studyManageRepository.save(studyManage);
	}

	/** 스터디 공지사항을 상세 조회하는 findByStudyNo 메소드입니다. */
	@Override
	public StudyManage findByStudyNo(Long studyNo) {
		StudyManage studyManage = studyManageRepository.findStudyManage(studyNo);
		return studyManage;
	}

	/** 스터디 공지사항을 수정하는 updateStudyManage 메소드입니다. */
	@Transactional
	@Override
	public StudyManage updateStudyManage(StudyManage studyManage, StudyManagePostReq studyManageDto) {
		studyManage.updateStudyManage(studyManageDto);
		return studyManage;
	}
	
	/** 스터디 공지사항을 삭제하는 deleteStudyManage 메소드입니다. */
	@Transactional
	@Override
	public void deleteStudyManage(StudyManage studyManage) {
		studyManageRepository.delete(studyManage);
		
	}
	

}
