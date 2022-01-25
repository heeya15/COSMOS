package com.nsnt.cosmos.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsnt.cosmos.api.request.SaveBoardDto;
import com.nsnt.cosmos.api.request.SaveStudyMemberDto;
import com.nsnt.cosmos.db.entity.StudyMember;
import com.nsnt.cosmos.db.repository.StudyMemberRepository;
@Service("StudyMemberService")
public class StudyMemberServiceImpl implements StudyMemberService {

	@Autowired // 이거를 jpa repository일 경우 해줘야함 -> 아니면 null 에러
	private StudyMemberRepository studyMemberRepository;
	
	@Override
	public StudyMember insertStudyMember(SaveStudyMemberDto saveStudyMemberDto) {
		System.out.println("들어옴?");
		StudyMember studymember = studyMemberRepository.save(saveStudyMemberDto.toEntity());
		
		return studymember;
	}

	@Override
	public List<StudyMember> findStudyMember(Long study_no) {
		System.out.println("스터디 번호 : >>>>>>>>>>>>>>"+study_no);
		List<StudyMember> studymember = studyMemberRepository.findStudyMember(study_no);	
		return studymember;
	}
	
	@Override
	public StudyMember findOneStudyMember(Long studymember_no) {
		StudyMember studymember = studyMemberRepository.findById(studymember_no).get();
		return studymember;
	}


	@Override
	public void deleteStudyMember(StudyMember studymember) {
		studyMemberRepository.delete(studymember);
	}
	@Transactional
	@Override
	public StudyMember updateStudyMemberScore(StudyMember studymember, SaveStudyMemberDto saveStudyMemberDto) {
		studymember.updateScore(saveStudyMemberDto);
		return studymember;
	}
}
