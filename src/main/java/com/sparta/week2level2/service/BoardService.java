package com.sparta.week2level2.service;

import com.sparta.week2level2.dto.BoardRequestDto;
import com.sparta.week2level2.model.Board;
import com.sparta.week2level2.model.User;
import com.sparta.week2level2.repository.BoardRepository;
import com.sparta.week2level2.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public String update(Long post_id, BoardRequestDto requestDto){
        Board board = boardRepository.findById(post_id).orElseThrow(
                () -> new NullPointerException("fail")
        );
        board.update(requestDto);
        return "success";
    }

    public BoardRequestDto getPost(Long post_id, Long user_id) {
        Board board = boardRepository.findById(post_id).orElseThrow(
                () -> new NullPointerException("해당 내용이 없습니다")
        );
        boolean Like = likeRepository.findByUseridAndPostid(user_id,post_id);

        return new BoardRequestDto(board,Like);
    }


}
