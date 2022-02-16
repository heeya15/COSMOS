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
public class SavePublicStudyMemberDto {
	@ApiModelProperty(name = "공개 스터디멤버 번호")
	private int publicmember_no;
	
	@ApiModelProperty(name = "스터디 장")
	private boolean leader;
	
}
