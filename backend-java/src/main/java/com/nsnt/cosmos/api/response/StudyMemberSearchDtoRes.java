package com.nsnt.cosmos.api.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.nsnt.cosmos.common.model.response.BaseResponseBody;
import com.nsnt.cosmos.db.entity.StudyMember;
import com.nsnt.cosmos.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그인 API ([POST] /api/v1/auth) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@AllArgsConstructor
@ApiModel("StudyMemberSearchDtoRes")
public class StudyMemberSearchDtoRes extends BaseResponseBody {
	@ApiModelProperty(name="유저 Name")
	String user_name;
	
	@ApiModelProperty(name="유저 Email")
	private String user_email;
	
	boolean attendance;
	
	int studytime; // 공부 시간
	int score; // 숫자
	
	
//	public static StudyMemberSearchDtoRes of(User user, StudyMember studymember) {
//		StudyMemberSearchDtoRes res = new StudyMemberSearchDtoRes();
//		res.setUser_name(user.getUserName());
//		res.setUser_email(user.getUserEmail());
//		res.setAttendance(studymember.isAttendance());
//		res.setStudytime(studymember.getStudytime());
//		res.setScore(studymember.getScore());
//		return res;
//	}
//	public static StudyMemberSearchDtoRes of( StudyMember studymember) {
//		StudyMemberSearchDtoRes res = new StudyMemberSearchDtoRes();
//	
//		res.setUser_name(studymember.getUser().getUserName());
//		res.setUser_email(studymember.getUser().getUserEmail());
//		res.setAttendance(studymember.isAttendance());
//		res.setStudytime(studymember.getStudytime());
//		res.setScore(studymember.getScore());
//		return res;
//	}
}
