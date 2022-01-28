package com.nsnt.cosmos.api.service;

import java.util.List;

import com.nsnt.cosmos.db.entity.ApplyMember;

/**
 * 스터디 신청 멤버 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ApplyMemberService {
	/** 스터디 멤버 신청한 유저를 생성하는 createMember 입니다 */
	public ApplyMember createMember(Long studyNo, String userId);
	
	/** 스터디 멤버 신청한 유저 정보를 가져오는 findAllByUserId 입니다 */
	public List<ApplyMember> findAllByStudyNo(Long study_no);

	/** 스터디 멤버 신청한 유저를 삭제(거절)하는 deleteApplyMember 입니다 */
	public void deleteApplyMember(ApplyMember applyMember);

	/** 신청 번호로 스터디 멤버 신청한 내용 조회하는 findByApplyMemberNo 입니다. */
	public ApplyMember findByApplyMemberNo(int applymember_no);

}
