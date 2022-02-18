package com.nsnt.cosmos.api.request;

import com.nsnt.cosmos.db.entity.BannedUser;
import com.nsnt.cosmos.db.entity.PublicStudyRoom;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 강퇴 유저 히스토리 API ([POST] /api/publicroom/register/bannedUser) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("BannedUserRequest")
public class BannedUserReq {
	@ApiModelProperty(name="강퇴자 No", example="0")
	int banneduser_no;
	
	@ApiModelProperty(name="유저 ID", example="")
	String user_id;

	@ApiModelProperty(name="스터디 No", example="1")
	String publicstudyroom_id;
	
	public BannedUser toEntity() {
		PublicStudyRoom publicstudyroom = new PublicStudyRoom();
		publicstudyroom.setPublicstudyroomId(publicstudyroom_id);
		
		return BannedUser.builder()
				.banneduserNo(banneduser_no)
				.userId(user_id)
				.publicStudyRoom(publicstudyroom)
				.build();
	}
	
}
