package com.nsnt.cosmos.api.request;

import com.nsnt.cosmos.db.entity.ApplyMember;
import com.nsnt.cosmos.db.entity.Study;
import com.nsnt.cosmos.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 스터디 신청 API ([POST] /api/study/applyMemeber/register/{study_no}) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("ApplyMemberRequest")
public class ApplyMemberReq {
	
	@ApiModelProperty(name="유저 ID", example="0")
	String applymember_no;
	
	@ApiModelProperty(name="스터디 No", example="1")
	Long study_no;

	@ApiModelProperty(name="유저 ID", example="")
	String user_id;
	
	
	public ApplyMember toEntity() {
		User user = new User();
		user.setUserId(user_id);
		
		Study study = new Study();
		study.setStudyNo(study_no);
		
		return ApplyMember.builder()
				.study_no(study)
				.userId(user)
				.build();
	}	
}
