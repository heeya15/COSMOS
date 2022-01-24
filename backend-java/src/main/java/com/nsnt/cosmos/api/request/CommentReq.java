package com.nsnt.cosmos.api.request;

import java.time.LocalDateTime;

import com.nsnt.cosmos.db.entity.Board;
import com.nsnt.cosmos.db.entity.Comment;
import com.nsnt.cosmos.db.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentReq {
	@ApiModelProperty(name = "댓글 번호")
	private int comment_no;
	
	@ApiModelProperty(name = "댓글 내용")
	private String content;
	
	@ApiModelProperty(name = "등록일")
	private LocalDateTime created_at;
	
	// User 테이블과의 관계에서의 참조키 (Foreign Key)
	@ApiModelProperty(name = "유저 id")
	private String user_id;
	
	// Board 테이블과의 관계에서의 참조키 (Foreign Key)
	@ApiModelProperty(name = "게시글 no")
	private Long board_no;
	
	public Comment toEntity() {
		User user = new User();
		user.setUserId(user_id);
		
		Board board = new Board();
		board.setBoardNo(board_no);
		
		return Comment.builder()
				.commentNo(comment_no)
				.content(content)
				.createdAt(created_at)
				.user(user)
				.board(board)
				.build();
	}
}
