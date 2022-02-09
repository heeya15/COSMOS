package com.nsnt.cosmos.api.service;

import java.util.List;

import com.nsnt.cosmos.db.entity.Board;
import com.nsnt.cosmos.db.entity.PrivateMember;


/**
 *	비공개 스터디룸 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface PrivateRoomService {
	/** 비공개 스터디 참가자 테이블에 데이터를 생성하는 createPrivateMember 입니다. */
    public PrivateMember createPrivateMember(String privatestudyroom_id, String user_id);
    /** 해당 비공개 스터디의 참가자 모든 정보를 가져오는 findAllPrivateMember 입니다. (목록 부분에 사용)*/
    public List<PrivateMember> findAllPrivateMember(String privatestudyroom_id);
    
    /** 해당 비공개 스터디 참가자가 방을 떠날시 참가자 명단에서 삭제하는 deleteBoard 입니다. */
    public void deletePrivateMember(String privatestudyroom_id, String user_id);
}
