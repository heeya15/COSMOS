package com.nsnt.cosmos.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsnt.cosmos.db.entity.Board;
import com.nsnt.cosmos.db.entity.User;
import io.swagger.annotations.ApiModelProperty;
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
public class SaveBoardDto {
  
    @ApiModelProperty(name = "게시글 id")
    private Long board_no;

    @ApiModelProperty(name = "게시글 제목")
    private String content_title;
    @ApiModelProperty(name = "말머리")
    private boolean header;

    @ApiModelProperty(name = "스터디 모집 인원")
    private int recruit_number;

    @ApiModelProperty(name = "내용")
    private String content;

    @ApiModelProperty(name = "모집 상태")
    private boolean content_status;

    @ApiModelProperty(name = "스터디 이름")
    private String study_name;

    @ApiModelProperty(name = "스터디 분류명")
    private String studytype_name;
    
    @ApiModelProperty(name = "유저 id")
    private String user_id;

    @ApiModelProperty(name = "스터디 번호")
    private Long study_no;
//    public SaveBoardDto(User entity) {
//        this.user_id = entity.getUserId();
//        this.user_name = entity.getUserName();
//        this.user_password = entity.getUserPassword();
//    }
//

    public Board toEntity(){
        User user = new User();
        user.setUserId(user_id);
        return Board.builder()
               .contentTitle(content_title)
               .header(header)
               .recruitNumber(recruit_number)
               .content(content)
               .contentStatus(content_status)
               .studyName(study_name)
               .studytypeName(studytype_name)
               .user(user)
               .studyNo(study_no)
               .build();
    }
}