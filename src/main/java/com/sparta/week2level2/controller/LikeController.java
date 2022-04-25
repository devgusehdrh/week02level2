package com.sparta.week2level2.controller;

import com.sparta.week2level2.config.auth.PrincipalDetails;
import com.sparta.week2level2.repository.LikeRepository;

import com.sparta.week2level2.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/posts")
public class LikeController {

    private final LikeRepository likeRepository;

    //게시글 좋아요
    @ResponseBody
    @PostMapping("{post_id}/like")
    public Long isLike(@PathVariable Long post_id, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        LikeService.likes(post_id,principalDetails.getUser().getId());

        return post_id;
    }

    //게시글 좋지 않아요
    @ResponseBody
    @PostMapping("/{post_id}/like/delete")
    public Long isLike(@PathVariable Long post_id){
        likeRepository.deleteById(post_id);

        return post_id;
    }
}
