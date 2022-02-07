package com.nsnt.cosmos.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsnt.cosmos.db.entity.PrivateMember;
import com.nsnt.cosmos.db.entity.PrivateStudyRoom;
import com.nsnt.cosmos.db.repository.PrivateRoomMemberRepository;

/**
 *	비공개 스터디 룸 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("PrivateRoomService")
public class PrivateRoomServiceImpl implements PrivateRoomService {
	@Autowired
	PrivateRoomMemberRepository privateRoomRepository;
	@Override
	public PrivateMember createPrivateMember(String privatestudyroom_id, String user_id) {
		PrivateMember privatemember = new PrivateMember();
		PrivateStudyRoom psroom = new PrivateStudyRoom();
		psroom.setPrivatestudyroomId(privatestudyroom_id);
		
		privatemember.setPrivateStudyRoom(psroom);
		privatemember.setUserId(user_id);
		privateRoomRepository.save(privatemember); 
		return privatemember;
	}
	@Override
	public List<PrivateMember> findAllPrivateMember(String privatestudyroom_id) {
		List<PrivateMember> list = privateRoomRepository.findPirvateMember(privatestudyroom_id);
		return list;
	}
	@Transactional
	@Override
	public void deletePrivateMember(String privatestudyroom_id, String user_id) {
		privateRoomRepository.deletePirvateMember(privatestudyroom_id, user_id);
	}
}
