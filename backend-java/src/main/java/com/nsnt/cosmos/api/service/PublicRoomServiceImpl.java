package com.nsnt.cosmos.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsnt.cosmos.api.request.BannedUserReq;
import com.nsnt.cosmos.api.request.PublicMemberRegisterDto;
import com.nsnt.cosmos.api.request.PublicStudyRoomRegisterDto;
import com.nsnt.cosmos.api.request.SavePublicStudyMemberDto;
import com.nsnt.cosmos.db.entity.PrivateMember;
import com.nsnt.cosmos.db.entity.PrivateStudyRoom;
import com.nsnt.cosmos.db.entity.PublicMember;
import com.nsnt.cosmos.db.entity.PublicStudyRoom;
import com.nsnt.cosmos.db.entity.User;
import com.nsnt.cosmos.db.repository.BannedUserRepository;
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
	
	@Autowired
	private BannedUserRepository bannedUserRepository;
	
	@Override
	public PublicStudyRoom createPublicStudyRoom(PublicStudyRoomRegisterDto publicroomDto, String user_id) {
		System.out.println("공개 스터디룸 생성 서비스 >>>>>>>>>>>>>>>들어옴?");
		
		System.out.println(publicroomDto.toString());
		System.out.println(user_id);
		// 1. 공개 스터디 룸 생성
		PublicStudyRoom publicstudyroom = PublicStudyRoomRepository.save(publicroomDto.toEntity());
		
		// 2. 공개 스터디룸 생성 후, 생성자가 공개 스터디 참가자로 추가.
	
		PublicRoomMemberRepository.insertPublicMember(true, publicroomDto.getPublicstudyroomId(),user_id); // 2. 스터디 멤버 생성	
		return publicstudyroom;
	}
	@Override
	public void createPublicMember(PublicMemberRegisterDto publicMemberDto, String user_id) {
		boolean leader  = publicMemberDto.isLeader();
		String public_studyroom_id = publicMemberDto.getPublicstudyroomId();
		System.out.println("공개 스터디 참가자 등록 service >>>>>>>>>>>>>>>>>>>DEBUG");
		System.out.println(leader +" "+ public_studyroom_id);
		PublicRoomMemberRepository.insertPublicMember(leader,public_studyroom_id,user_id); 	
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
	@Override
	public List<PublicStudyRoom> findAllPublicStudyRoom() {
		List<PublicStudyRoom> list = PublicStudyRoomRepository.findAllPublicStudyRoom();
		return list;
	}
	
	@Override
	@Transactional
	public void createBannedUser(String publicstudyroom_id, String user_id) {
		BannedUserReq bannedUserReq = new BannedUserReq();
		
		bannedUserReq.setUser_id(user_id);
		bannedUserReq.setPublicstudyroom_id(publicstudyroom_id);
		
		bannedUserRepository.save(bannedUserReq.toEntity());
	}
	
	@Override
	public boolean isBannedCheck(String publicstudyroom_id, String user_id) {
		int count = bannedUserRepository.findByUserId(publicstudyroom_id, user_id);
		
		if(count>0) return true;
		else return false;
	}
	@Override
	public PublicMember findOnePublicStudyMember(int studymember_no) {
		PublicMember result = PublicRoomMemberRepository.findById(studymember_no).get();
		return result;
	}
	@Transactional
	@Override
	public PublicMember updatePublicStudyMemberAuthority(PublicMember publicmember, SavePublicStudyMemberDto savePublicStudyMemberDto) {
		publicmember.updateLeader(savePublicStudyMemberDto.isLeader());
		return publicmember;
	}
}
