package com.nsnt.cosmos.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsnt.cosmos.api.request.StudyPostReq;
import com.nsnt.cosmos.db.entity.Board;
import com.nsnt.cosmos.db.entity.PrivateStudyRoom;
import com.nsnt.cosmos.db.entity.Study;
import com.nsnt.cosmos.db.entity.StudyMember;
import com.nsnt.cosmos.db.entity.StudyType;
import com.nsnt.cosmos.db.entity.User;
import com.nsnt.cosmos.db.repository.PrivateStudyRoomRepository;
import com.nsnt.cosmos.db.repository.PublicStudyRoomRepository;
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
	
	@Autowired
	PrivateStudyRoomRepository privateStudyRoomRepository;
	
	@Autowired
	PublicStudyRoomRepository publicStudyRoomRepository;
	/** 스터디를 생성하는 createStudy 메소드입니다. */
	@Transactional
	@Override
	public Study createStudy(StudyPostReq studyRegisterInfo) {
		String url = studyRegisterInfo.getUrl();
		String ptroom_id = url.substring(url.lastIndexOf("/")+1); // 특정 문자 이후의 문자열 저장.
		
		Study study = new Study();
		study.setStudyName(studyRegisterInfo.getStudyName());
		study.setUrl(studyRegisterInfo.getUrl());
		study.setImage(studyRegisterInfo.getImage());
		study.setTotalMember(studyRegisterInfo.getTotalMember());
		study.setNumberOfMember(1); // 처음에 스터디장 포함 인원은 1명이여서 1로 셋팅.
		study.setCreatedAt(LocalDateTime.now());
		study.setStudyRule(studyRegisterInfo.getStudyRule());
		study.setStudyPassword(studyRegisterInfo.getStudyPassword());
		StudyType studyType = new StudyType();
		studyType.setStudytypeNo(studyRegisterInfo.getStudytypeNo());
		study.setStudyType(studyType); // 스터디 분류
		System.out.println(study.toString());
		studyRepository.save(study); // 1. 스터디 생성
		
		StudyMember studyMember = new StudyMember();
		
		User user = new User();
		user.setUserId(studyRegisterInfo.getUserId());
		studyMember.setUser(user);
		studyMember.setStudy(study);
		studyMember.setAuthority(true);
		studyMember.setLeader(true);
		
		studyMemberRepository.save(studyMember); // 2. 스터디 멤버 생성
		
		// 3. 되자마자 비공개 스터디 룸에, 데이터 셋팅 해줌
		
		PrivateStudyRoom psroom = new PrivateStudyRoom();
		System.out.println("서브 스트링 결과 "+ ptroom_id);
		psroom.setPrivatestudyroomId(ptroom_id);
		psroom.setStudy(study);
		System.out.println("비공개 스터디룸 데이터 들감?"+ psroom.toString());
		privateStudyRoomRepository.save(psroom);
		
		return study;
	}

	/** 스터디 이름을 중복검사하는 checkStudyNameDuplicate 메소드입니다. */
	@Override
	public boolean checkStudyNameDuplicate(String studyName) {
		return studyRepository.existsByStudyName(studyName);
	}

	/** 스터디 url을 중복검사하는 checkUrlDuplicate 메소드입니다. */
	@Override
	public boolean checkUrlDuplicate(String url) {
		boolean public_result = publicStudyRoomRepository.existsByUrl(url);
		boolean private_result = studyRepository.existsByUrl(url);
		
		System.out.println("공개방 중복 여부"+public_result);
		System.out.println("비공개방 중복 여부"+private_result);
		boolean result;
		if(public_result==true || private_result == true) {
			result = true; // 중복
		}else result =false;
		return result;
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
