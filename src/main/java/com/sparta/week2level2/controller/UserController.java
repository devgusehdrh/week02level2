package com.sparta.week2level2.controller;

import com.sparta.week2level2.dto.UserRequestDto;
import com.sparta.week2level2.model.User;
import com.sparta.week2level2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("api")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @ResponseBody
    @PostMapping("register")
    public String register(@RequestBody UserRequestDto requestDto){
        String username = requestDto.getUsername();
        String pw = passwordEncoder.encode(requestDto.getPw());
        String nickname = requestDto.getNickname();
        User user = new User(username,pw,nickname);
        userRepository.save(user);
        return "success";
    }
}
