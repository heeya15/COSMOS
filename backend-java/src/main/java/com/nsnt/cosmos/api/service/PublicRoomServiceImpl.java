package com.nsnt.cosmos.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsnt.cosmos.api.request.PublicMemberRegisterDto;
import com.nsnt.cosmos.api.request.PublicStudyRoomRegisterDto;
import com.nsnt.cosmos.db.entity.PrivateMember;
import com.nsnt.cosmos.db.entity.PrivateStudyRoom;
import com.nsnt.cosmos.db.entity.PublicMember;
import com.nsnt.cosmos.db.entity.PublicStudyRoom;
import com.nsnt.cosmos.db.entity.User;
import com.nsnt.cosmos.db.repository.PublicRoomMemberRepository;
import com.nsnt.cosmos.db.repository.PublicStudyRoomRepository;

/**
 *	공개 스터디 룸 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("PublicRoomService")
public class PublicRoomServiceImpl implements PublicRoomService {
	@Autowired // 이거를 jpa repository일 경우 해줘야함 -> 아니면 null 에러
	private PublicStudyRoomRepository PublicStudyRoomRepository;
	
	@Autowired // 각각 repository Autowired 해줘야함 안그러면 null 에러 남.
	private PublicRoomMemberRepository PublicRoomMemberRepository;
	
	@Override
	public PublicStudyRoom createPublicStudyRoom(PublicStudyRoomRegisterDto publicroomDto, String user_id) {
		System.out.println("공개 스터디룸 생성 서비스 >>>>>>>>>>>>>>>들어옴?");
		
		System.out.println(publicroomDto.toString());
		System.out.println(user_id);
		// 1. 공개 스터디 룸 생성
		PublicStudyRoom publicstudyroom = PublicStudyRoomRepository.save(publicroomDto.toEntity());
		
		// 2. 공개 스터디룸 생성 후, 생성자가 공개 스터디 참가자로 추가.
	
		PublicRoomMemberRepository.insertPublicMember(true, 0L, publicroomDto.getPublicstudyroomId(),user_id); // 2. 스터디 멤버 생성	
		return publicstudyroom;
	}
	@Override
	public void createPublicMember(PublicMemberRegisterDto publicMemberDto, String user_id) {
		boolean attendance  = publicMemberDto.isAttendance();
		Long score = publicMemberDto.getMember_score();
		String public_studyroom_id = publicMemberDto.getPublicstudyroomId();
		System.out.println("공개 스터디 참가자 등록 service >>>>>>>>>>>>>>>>>>>DEBUG");
		System.out.println(attendance +" "+ score +" "+ public_studyroom_id);
		PublicRoomMemberRepository.insertPublicMember(attendance,score,public_studyroom_id,user_id); 	
	}
	@Override
	public List<PublicMember> findAllPublicMember(String publicroom_id) {
		List<PublicMember> list = PublicRoomMemberRepository.findPublicMember(publicroom_id);
		return list;
	}
	@Override
	public void deletePublicMember(String public_study_room_id, String user_id) {
		PublicRoomMemberRepository.deletePublicMember(public_study_room_id, user_id);
	}
	@Override
	public void deletePublicRoom(String publicstudy_room_id) {	// 공개 스터디방 삭제
		PublicStudyRoomRepository.deletePublicRoom(publicstudy_room_id);
	}
}
