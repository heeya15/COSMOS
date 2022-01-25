package com.nsnt.cosmos.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsnt.cosmos.api.request.ApplyMemberReq;
import com.nsnt.cosmos.db.entity.ApplyMember;
import com.nsnt.cosmos.db.repository.ApplyMemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ApplyMemberService")
public class ApplyMemberServiceImpl implements ApplyMemberService {

	@Autowired
	private ApplyMemberRepository applyMemberRepository;
	
	/** 스터디 멤버 신청한 유저를 생성하는 createMember 입니다 */
	@Override
	@Transactional
	public ApplyMember createMember(Long studyNo, String userId) {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> studyNo : {}, userId : {}", studyNo, userId);
		ApplyMemberReq applyMemberReq = new ApplyMemberReq();
		
		applyMemberReq.setStudy_no(studyNo);
		applyMemberReq.setUser_id(userId);
		
		
		ApplyMember applyMember = applyMemberRepository.save(applyMemberReq.toEntity());
		
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> applyMember : {}", applyMember);
		return applyMember;
	}
	
	/** 스터디 멤버 신청한 유저 정보를 가져오는 findAllByUserId 입니다 */
	@Override
	@Transactional
	public List<ApplyMember> findAllByStudyNo(Long study_no) {
		log.debug(">>>>>>>>>>>>>>>>>> findAllByStudyNo");
		List<ApplyMember> applyMembers = applyMemberRepository.findAllByStudyNo(study_no);
		return applyMembers;
	}
	
	/** 스터디 멤버 신청한 유저를 삭제(거절)하는 deleteApplyMember 입니다 */
	@Override
	@Transactional
	public void deleteApplyMemeber(ApplyMember applyMember) {
		applyMemberRepository.delete(applyMember);
	}

	/** 신청 번호로 스터디 멤버 신청한 내용 조회하는 findByApplyMemberNo 입니다. */
	public ApplyMember findByApplyMemberNo(int applymember_no) {
		ApplyMember applyMember = applyMemberRepository.findById(applymember_no).get();
		return applyMember;
	}

}
