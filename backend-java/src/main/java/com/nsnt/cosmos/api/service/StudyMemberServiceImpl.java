package com.nsnt.cosmos.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsnt.cosmos.api.request.SaveBoardDto;
import com.nsnt.cosmos.api.request.SaveStudyMemberDto;
import com.nsnt.cosmos.db.entity.Board;
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
	public List<StudyMember> findStudyMmeber(Long study_no) {
		System.out.println("스터디 번호 : >>>>>>>>>>>>>>"+study_no);
		List<StudyMember> studymember = studyMemberRepository.findStudyMember(study_no);	
		return studymember;
	}

	@Override
	public void deleteStudyMember(StudyMember board) {
		// TODO Auto-generated method stub

	}

	@Override
	public StudyMember updateStudyMemberScore(StudyMember studymember, SaveBoardDto saveBoardDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
