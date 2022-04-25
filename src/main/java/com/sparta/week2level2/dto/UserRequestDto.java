package com.sparta.week2level2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class UserRequestDto {
    private Long id;

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z\\d]{2,10}$", message = "아이디는 2~10자 영문 대소문자, 숫자를 사용해주세요")
    private String username;


    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{4,16}", message = "비밀번호는 4~16자 영문 대 소문자, 숫자, 특수문자를 사용해주세요")
    private String pw;

    @NotBlank(message = "닉네임을 입력해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{3,10}$", message = "닉네임은 특수문자를 제외한 3~10자리로 작성해주세요")
    private String nickname;
    }
