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
    private final LikeService likeService;

    //게시글 좋아요
    @ResponseBody
    @PostMapping("{post_id}/like")
    public String isLike(
            @PathVariable Long post_id,
            @AuthenticationPrincipal PrincipalDetails principalDetails){
        return likeService.likes(post_id,principalDetails.getUser().getId());
    }

    //게시글 좋지 않아요
    @ResponseBody
    @PostMapping("/{post_id}/like/delete")
    public String isDislike(@PathVariable Long post_id,
                            @AuthenticationPrincipal PrincipalDetails principalDetails){
        return likeService.dislikes(post_id,principalDetails.getUser().getId());}
}
