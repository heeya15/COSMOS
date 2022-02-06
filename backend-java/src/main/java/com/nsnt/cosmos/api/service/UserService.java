package com.nsnt.cosmos.api.service;

import com.nsnt.cosmos.api.request.UserUpdateDto;
import com.nsnt.cosmos.api.response.UserLeaderDtoRes;
import com.nsnt.cosmos.api.request.UserRegisterPostReq;
import com.nsnt.cosmos.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserId(String userId);
	boolean checkUserId(String userid);
	int checkUserEmail(String userEmail);
	boolean deleteByUserId(User user);
	void updateUser(UserUpdateDto updateUserDto);
	
	UserLeaderDtoRes isLeader(String user_id, String study_no);
}
