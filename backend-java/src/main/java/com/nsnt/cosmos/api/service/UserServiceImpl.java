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
import com.nsnt.cosmos.api.request.UserRegisterPostReq;
import com.nsnt.cosmos.db.entity.User;
import com.nsnt.cosmos.db.repository.UserRepository;
import com.nsnt.cosmos.db.repository.UserRepositorySupport;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserId(userRegisterInfo.getUserId());
		user.setUserName(userRegisterInfo.getUserName());
		user.setUserEmail(userRegisterInfo.getUserEmail());
		user.setTotalTime(userRegisterInfo.getTotalTime());
		user.setTotalAttendance(userRegisterInfo.getTotalAttendance());
		user.setJoinDate(LocalDateTime.now());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setUserPassword(passwordEncoder.encode(userRegisterInfo.getUserPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByUserId(userId).get();
		return user;
	}

	@Override
	public boolean checkUserId(String userId) {
		boolean result = userRepositorySupport.findByUserIdEquals(userId);
		return result;
	}
	
	@Override
	public int checkUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public boolean deleteByUserId(User user) {
		userRepository.delete(user);
		return true;
	}

	@Transactional
	@Override
	public void updateUser(UserUpdateDto userUpdateDto) {
		System.out.println("수정 들어옴?");
		User user = userRepositorySupport.findUserByUserId(userUpdateDto.getUser_id()).get();
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		String password = passwordEncoder.encode(userUpdateDto.getUser_password());
		user.updateUser(userUpdateDto.getUser_name(),password);
	}

	@Override
	public UserLeaderDtoRes isLeader(String user_id, String study_no) {
		
		return userRepository.isLeader(user_id, study_no);
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
