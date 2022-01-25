package com.nsnt.cosmos.api.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes{
	
	
	@ApiModelProperty(name="User ID")
	String user_id;
	
	@ApiModelProperty(name="User email")
	String user_email;
	
	@ApiModelProperty(name="User name")
	String user_name;
	
	@ApiModelProperty(name="User pw")
	String user_password;
	
	public static UserRes of(User user) {
		UserRes res = new UserRes();
		res.setUser_id(user.getUserId());
		res.setUser_name(user.getUserName());
		res.setUser_email(user.getUserEmail());
		res.setUser_password(user.getUserPassword());
		return res;
	}
}
