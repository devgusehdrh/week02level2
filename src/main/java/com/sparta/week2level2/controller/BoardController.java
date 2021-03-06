package com.sparta.week2level2.controller;

import com.sparta.week2level2.config.auth.PrincipalDetails;
import com.sparta.week2level2.dto.BoardRequestDto;
import com.sparta.week2level2.model.Board;
import com.sparta.week2level2.repository.BoardRepository;
import com.sparta.week2level2.service.BoardService;
import com.sparta.week2level2.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final FileService fileService;

    // 게시글 목록 조회
    @ResponseBody
    @GetMapping("posts")
    public List<Board> getPost(){
            return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 세부 조회
    @ResponseBody
    @GetMapping("posts/{post_id}")
    public BoardRequestDto detailPost(@PathVariable Long post_id,
                                      @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
        return boardService.getPost(post_id,principalDetails.getUser().getId());
    }

    // 게시글 삭제
    @ResponseBody
    @PostMapping("posts/{post_id}/delete")
    public String deletePost(@PathVariable Long post_id){
        boardRepository.findById(post_id).orElseThrow(
                () -> new NullPointerException("fail")
        );
            boardRepository.deleteById(post_id);
            return "success";

    }

    //게시글 수정
    @ResponseBody
    @PatchMapping("posts/{post_id}")
    public String updatePost(@PathVariable Long post_id, @RequestBody BoardRequestDto requestDto){
        return boardService.update(post_id,requestDto);
    }

    //게시글 추가
    @ResponseBody
    @PostMapping(value = "posts",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long writePost(
            @RequestPart MultipartFile picture,
            @RequestPart String content,
            @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        return fileService.uploadFile(picture,content,principalDetails);

    }

}
