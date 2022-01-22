package com.nsnt.cosmos.api.request;

import java.time.LocalDateTime;

import com.nsnt.cosmos.db.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor /** Cannot construct instance of~ error를 해결해주었다.  파라미터가 없는 생성자 만들어줌* */ 
public class UserUpdateDto {

	@ApiModelProperty(name = "유저 id")
	private String user_id;
	@ApiModelProperty(name = "유저 Password")
	private String user_password;

	@ApiModelProperty(name = "유저 이름")
	private String user_name;

	public UserUpdateDto(User entity) {
		this.user_id = entity.getUserId();
		this.user_name = entity.getUserName();
		this.user_password = entity.getUserPassword();
	}

//    public User toEntity(){
//        return User.builder()
//                .userName(user_name)
//                .userPassword(user_password)     
//                .build();
//    }
}
