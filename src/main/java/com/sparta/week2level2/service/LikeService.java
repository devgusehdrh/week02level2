package com.sparta.week2level2.service;

import com.sparta.week2level2.model.Board;
import com.sparta.week2level2.model.Likes;
import com.sparta.week2level2.model.User;
import com.sparta.week2level2.repository.BoardRepository;
import com.sparta.week2level2.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;

    public String likes(Long post_id, Long user_id) {
        Board board = boardRepository.findById(post_id).orElseThrow(
                ()-> new NullPointerException("해당 게시물이 존재하지않습니다.")
        );
        if (!likeRepository.findByUseridAndPostid(post_id, user_id)) {
            Board postid = null;
            postid.setId(post_id);
            User userid = null;
            userid.setId(user_id);
            Likes likes = new Likes(postid,userid);
            likeRepository.save(likes);
            return "success";
        }
        return "fail";
    }
    public String dislikes(Long post_id, Long user_id) {
        Board board = boardRepository.findById(post_id).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지않습니다.")
        );
        if(board.getUser_id().equals(user_id)) {
            if (!likeRepository.findByUseridAndPostid(post_id, user_id)) {
                likeRepository.deleteById(post_id);
                return "success";
            }
        }
        return "fail";
    }
}
