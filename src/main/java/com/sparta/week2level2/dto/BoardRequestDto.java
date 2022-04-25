package com.sparta.week2level2.dto;


import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String content;
    private String picture;
    private String nickname;
    private Long likeCount;
}
