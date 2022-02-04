package com.nsnt.cosmos.api.response;
/**
 * 게시글 댓글 전체 조회 API ([GET] /searchAll/{board_no}) 요청에 대한 응답값 정의.
 */
public interface CommentSearchDtoRes {
    int getComment_no();
    String getContent();
    String getCreated_at();
    String getUser_id();
    int getBoard_no();
}