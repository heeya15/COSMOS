package com.nsnt.cosmos.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsnt.cosmos.api.request.StudyPostReq;
import com.nsnt.cosmos.db.entity.Board;
import com.nsnt.cosmos.db.entity.Study;
import com.nsnt.cosmos.db.entity.StudyMember;
import com.nsnt.cosmos.db.entity.StudyType;
import com.nsnt.cosmos.db.entity.User;
import com.nsnt.cosmos.db.repository.StudyMemberRepository;
import com.nsnt.cosmos.db.repository.StudyRepository;
import com.nsnt.cosmos.db.repository.StudyTypeRepository;

/**
 *	스터디 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("studyService")
public class StudyServiceImpl implements StudyService {
	
	@Autowired
	StudyRepository studyRepository;
	
	@Autowired
	StudyTypeRepository studyTypeRepository;
	
	@Autowired
	StudyMemberRepository studyMemberRepository;
	
	/** 스터디를 생성하는 createStudy 메소드입니다. */
	@Transactional
	@Override
	public Study createStudy(StudyPostReq studyRegisterInfo) {
		Study study = new Study();
		study.setStudyName(studyRegisterInfo.getStudyName());
		study.setUrl(studyRegisterInfo.getUrl());
		study.setImage(studyRegisterInfo.getImage());
		study.setTotalMember(studyRegisterInfo.getTotalMember());
		study.setNumberOfMember(1);
		study.setCreatedAt(LocalDateTime.now());
		study.setStudyRule(studyRegisterInfo.getStudyRule());
		study.setStudyPassword(studyRegisterInfo.getStudyPassword());
		StudyType studyType = new StudyType();
		studyType.setStudytypeNo(studyRegisterInfo.getStudytypeNo());
		study.setStudyType(studyType);
		
		StudyMember studyMember = new StudyMember();
		
		User user = new User();
		user.setUserId(studyRegisterInfo.getUserId());
		studyMember.setUser(user);
		studyMember.setStudy(study);
		studyMember.setAuthority(true);
		studyMember.setLeader(true);
		
		studyMemberRepository.save(studyMember);
		
		
		return studyRepository.save(study);
	}

	/** 스터디 이름을 중복검사하는 checkStudyNameDuplicate 메소드입니다. */
	@Override
	public boolean checkStudyNameDuplicate(String studyName) {
		return studyRepository.existsByStudyName(studyName);
	}

	/** 스터디 url을 중복검사하는 checkUrlDuplicate 메소드입니다. */
	@Override
	public boolean checkUrlDuplicate(String url) {
		return studyRepository.existsByUrl(url);
	}

	/** 스터디 번호에 해당하는 스터디를 찾는 findByStudyNo 메소드입니다. */
	@Override
	public Study findByStudyNo(Long studyNo) {
		Study study = studyRepository.findById(studyNo).get();
		return study;
	}

	/** 해당 스터디를 삭제하는 deleteStudy 메소드입니다. */
	@Override
	public void deleteStudy(Study study) {
		studyRepository.delete(study);
	}

	/** 해당 스터디 정보를 수정하는 updateStudy 메소드입니다. */
	@Transactional
	@Override
	public Study updateStudy(Study study, StudyPostReq studyUpdateInfo) {
		study.updateStudy(studyUpdateInfo);
		return study;
	}

	/** 멤버가 소속된 스터디 리스트를 찾는 findMemberStudy 메소드입니다. */
	@Override
	public List<Study> findMemberStudy(String userId) {
		return studyRepository.findMemberStudy(userId);
	}

	@Override
	public List<StudyType> findAllStudyType() {
		List<StudyType> studyType = studyTypeRepository.findAllStudyType();
	    return studyType;
	}
	
	@Override
	public void updateNumberOfStudyMember(Long studyNo) {
		studyRepository.updateNumberOfStudyMembers(studyNo);
	}

}
