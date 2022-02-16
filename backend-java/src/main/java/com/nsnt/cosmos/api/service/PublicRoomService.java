package com.nsnt.cosmos.api.service;

import java.util.List;

import com.nsnt.cosmos.api.request.PublicMemberRegisterDto;
import com.nsnt.cosmos.api.request.PublicStudyRoomRegisterDto;
import com.nsnt.cosmos.api.request.SavePublicStudyMemberDto;
import com.nsnt.cosmos.db.entity.PublicMember;
import com.nsnt.cosmos.db.entity.PublicStudyRoom;

/**
 *	공개 스터디룸 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface PublicRoomService {
	/** 공개 스터디룸을 생성하는 createPublicStudyRoom 입니다 **/
	PublicStudyRoom createPublicStudyRoom(PublicStudyRoomRegisterDto publicroomDto, String user_id);
	
	/** 공개 스터디 참가자 테이블에 데이터를 생성하는 createPublicMember 입니다. */
    public void createPublicMember( PublicMemberRegisterDto publicMemberDto, String user_id);
    /** 해당 공개 스터디의 참가자 모든 정보를 가져오는 findAllPublicMember 입니다. (목록 부분에 사용)*/
    public List<PublicMember> findAllPublicMember(String public_room_id);
    
    /** 해당 공개 스터디 참가자가 방을 떠날시 참가자 명단에서 삭제하는 deletePublicMember 입니다. */
    public void deletePublicMember(String publicstudy_room_id, String user_id);
    
    /** 해당 공개 스터디방에 참가자가 아무도 없으면 해당 공개 스터디방 삭제하는 deletePublicRoom 입니다. */
    public void deletePublicRoom(String publicstudy_room_id);
    
    
    public List<PublicStudyRoom> findAllPublicStudyRoom();
    
    /** 해당 공개 스터디방으로부터 강퇴 당한 유저를 기록하기위한 createBannedUser 입니다. */
    public void createBannedUser(String publicstudyroom_id, String user_id);
    
    /** 현재 유저에대해 해당 공개 스터디방으로부터 강퇴 당한지 여부를 체크하기위한 isBannedCheck 입니다. */
    public boolean isBannedCheck(String publicstudyroom_id, String user_id);
    
    /** 스터디 멤버중 해당 publicmember_no 로 한 회원의 정보만 찾는 findOnePublicStudyMember 입니다.*/
    public PublicMember findOnePublicStudyMember(int studymember_no);
    
	/** 공개 스터디 멤버중 스터디 장의 임시 역할을 하게 하거나 회수하는 권한을 수정하는 updatePublicStudyMemberAuthority 입니다.*/
	public PublicMember updatePublicStudyMemberAuthority(PublicMember publicmember,
										SavePublicStudyMemberDto savePublicStudyMemberDto);
}