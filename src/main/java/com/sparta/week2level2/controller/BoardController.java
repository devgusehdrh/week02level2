package com.sparta.week2level2.controller;

import com.sparta.week2level2.config.auth.PrincipalDetails;
import com.sparta.week2level2.dto.BoardRequestDto;
import com.sparta.week2level2.dto.FileRequestDto;
import com.sparta.week2level2.model.Board;
import com.sparta.week2level2.repository.BoardRepository;
import com.sparta.week2level2.service.BoardService;
import com.sparta.week2level2.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final FileService fileService;

    @ResponseBody
    @GetMapping("posts")
    public List<Board> getPost(@RequestParam Long userId){
            return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 세부 조회
    @ResponseBody
    @GetMapping("posts/{post_id}")
    public Optional<Board> detailPost(@PathVariable Long post_id) throws Exception{

        return boardRepository.findById(post_id);
    }

    // 게시글 삭제
    @ResponseBody
    @PostMapping("posts/{post_id}/delete")
    public Long deletePost(@PathVariable Long post_id){
        boardRepository.deleteById(post_id);
        return post_id;
    }

    //게시글 수정
    @ResponseBody
    @PatchMapping("posts/{post_id}")
    public Long updatePost(@PathVariable Long post_id, @RequestBody BoardRequestDto requestDto){
        return boardService.update(post_id,requestDto);
    }

    //게시글 추가
    @PostMapping(value = "posts",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long writePost(
            @RequestBody FileRequestDto requestDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        return fileService.uploadFile(requestDto,principalDetails);

    }

}
