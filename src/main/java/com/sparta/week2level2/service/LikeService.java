package com.sparta.week2level2.service;

import com.sparta.week2level2.model.Like;
import com.sparta.week2level2.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;

//    public static void likes(Long post_id, Long id) {
//        Like like = new Like(post_id,id);
//
//
//    }
}
