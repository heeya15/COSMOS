package com.nsnt.cosmos.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor /** Cannot construct instance of~ error를 해결해주었다.  파라미터가 없는 생성자 만들어줌* */ 
@ToString
public class SaveUserHistoryDto {

	private String state;
	
//    @ApiModelProperty(name = "스터디 시작시간")
//    private LocalDateTime userStartTime;
//	
//	
//    @ApiModelProperty(name = "스터디 종료시간")
//    private LocalDateTime userFinishTime;
}