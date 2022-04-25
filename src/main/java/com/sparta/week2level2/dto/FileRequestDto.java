package com.sparta.week2level2.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class FileRequestDto {
    private MultipartFile picture;
    private String content;
}
