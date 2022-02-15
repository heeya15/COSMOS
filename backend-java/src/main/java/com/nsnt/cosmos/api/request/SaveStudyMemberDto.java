package com.nsnt.cosmos.api.request;

import com.nsnt.cosmos.db.entity.Study;
import com.nsnt.cosmos.db.entity.StudyMember;
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
public class SaveStudyMemberDto {
	@ApiModelProperty(name = "스터디멤버 번호")
	private Long studymember_no;
	
	@ApiModelProperty(name = "출석여부")
	private boolean attendance;
	
	@ApiModelProperty(name = "스터디 장")
	private boolean leader;
	
	@ApiModelProperty(name = "스터디 장의 권한 사용 여부")
	private boolean authority;

	@ApiModelProperty(name = "스터디 번호")
	private Long study_no;
	
	@ApiModelProperty(name = "유저 id")
	private String user_id;
	
	@ApiModelProperty(name = "스터디 해당 멤버 점수")
	private int score;
	
//	public SaveBoardDto(User entity) {
//		this.user_id = entity.getUserId();
//		this.user_name = entity.getUserName();
//		this.user_password = entity.getUserPassword();
//	}
//	
	
	public StudyMember toEntity(){
		Study study = new Study();
		study.setStudyNo(study_no);
		User user = new User();
		user.setUserId(user_id);	
        return StudyMember.builder()
               .study(study)
               .user(user)
               .build();
    }
}
