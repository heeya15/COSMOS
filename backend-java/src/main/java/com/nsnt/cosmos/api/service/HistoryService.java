package com.nsnt.cosmos.api.service;

import com.nsnt.cosmos.api.request.UserUpdateDto;
import com.nsnt.cosmos.api.response.UserDtoRes;
import com.nsnt.cosmos.api.response.UserLeaderDtoRes;

import java.util.List;

import com.nsnt.cosmos.api.request.SaveUserHistoryDto;
import com.nsnt.cosmos.api.request.UserRegisterPostReq;
import com.nsnt.cosmos.db.entity.User;
import com.nsnt.cosmos.db.entity.UserHistory;

/**
 * 히스토리 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface HistoryService {
	UserHistory insertUserHistory(SaveUserHistoryDto userhisroyDto, String user_id);
	
}
