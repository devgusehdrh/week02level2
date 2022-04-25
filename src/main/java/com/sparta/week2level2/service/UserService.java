package com.sparta.week2level2.service;

import com.sparta.week2level2.dto.UserRequestDto;
import com.sparta.week2level2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
// javax로 시작되는 Transactional 패키지를 임포트할 경우 readOnly 옵션을 사용할 수 없다.
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    /* 아이디, 닉네임, 이메일 중복 여부 확인 */
    @Transactional(readOnly = true)
    public void checkUsernameDuplication(UserRequestDto requestDto) {
        boolean usernameDuplicate = userRepository.existsByUsername(requestDto.getUsername());
        if (usernameDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void checkNicknameDuplication(UserRequestDto requestDto) {
        boolean nicknameDuplicate = userRepository.existsByNickname(requestDto.getNickname());
        if (nicknameDuplicate) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void checkPwContainsNickname(UserRequestDto requestDto){
        boolean pwContainsNickname = requestDto.getPw().contains(requestDto.getNickname());
        if (pwContainsNickname){
            throw new IllegalStateException("패스워드에는 닉네임이 포함되면 안됩니다.");
        }
    }
}
