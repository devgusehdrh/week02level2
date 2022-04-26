package com.sparta.week2level2.dto;


import com.sparta.week2level2.model.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardRequestDto {
    private String content;
    private String picture;
    private String nickname;
    private Long likeCount;
    private boolean Like;
    private LocalDateTime modifiedAt;


    public BoardRequestDto(Board board, boolean Like){
        this.content = board.getContent();
        this.picture = board.getPicture();
        this.nickname = board.getNickname();
        this.likeCount = board.getLikeCount();
        this.Like = Like;
        this.modifiedAt = board.getModifiedAt();
    }
}
