package com.nsnt.cosmos.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor /** Cannot construct instance of~ error를 해결해주었다.  파라미터가 없는 생성자 만들어줌* */ 
public class PublicMemberRegisterDto {

	@ApiModelProperty(name = "출석여부")
	private boolean attendance;

	@ApiModelProperty(name = "점수")
	private Long member_score;
	
	
	@ApiModelProperty(name="공개스터디룸 아이디", example="")
	String publicstudyroomId;
}
