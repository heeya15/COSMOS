package com.nsnt.cosmos.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsnt.cosmos.api.request.UserUpdateDto;
import com.nsnt.cosmos.api.response.UserDtoRes;
import com.nsnt.cosmos.api.response.UserLeaderDtoRes;
import com.nsnt.cosmos.api.request.SaveBoardDto;
import com.nsnt.cosmos.api.request.UserRegisterPostReq;
import com.nsnt.cosmos.db.entity.Board;
import com.nsnt.cosmos.db.entity.StudyType;
import com.nsnt.cosmos.db.entity.User;
import com.nsnt.cosmos.db.repository.StudyTypeRepository;
import com.nsnt.cosmos.db.repository.UserRepository;
import com.nsnt.cosmos.db.repository.UserRepositorySupport;

/**
 *	스터디 타입 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("StudyTypeService")
public class StudyTypeServiceImpl implements StudyTypeService {

	@Autowired
	StudyTypeRepository studyTypeRepository;

	@Override
	public void createStudyType(String studytype_name) {
		studyTypeRepository.insertStudyType(studytype_name);
	}
	
	@Override
	public StudyType findByStudytypeNo(int studytype_no) {
		StudyType result = studyTypeRepository.findById(studytype_no).get();
		return result;
	}
	
	@Transactional // 해당 어노테이션 안 해주면 데이터베이스에 반영 안됨
	@Override
	public StudyType updateStudyType(StudyType studytype, String studytype_name) {
		System.out.println("찍히나?"+studytype.toString());  
		studytype.updateStudyTypeName(studytype_name);
	    return studytype;
	}
	@Transactional // 해당 어노테이션 안 해주면 데이터베이스에 반영 안됨
	@Override
	public void deleteStudyType(String studytype_name) {
		studyTypeRepository.deleteStudyType(studytype_name);
	}
}
