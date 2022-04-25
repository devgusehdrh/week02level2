package com.sparta.week2level2.dto;

import com.sparta.week2level2.model.Board;
import com.sparta.week2level2.model.User;
import lombok.Getter;

@Getter
public class LikeRequestDto {
    private Board post_id;
    private User user_id;
}
