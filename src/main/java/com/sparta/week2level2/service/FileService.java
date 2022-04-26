package com.sparta.week2level2.service;

import com.sparta.week2level2.config.auth.PrincipalDetails;
import com.sparta.week2level2.model.Board;
import com.sparta.week2level2.model.User;
import com.sparta.week2level2.repository.BoardRepository;
import com.sparta.week2level2.repository.FileRepository;
import com.sparta.week2level2.model.Files;
import com.sparta.week2level2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 이미지 파일 정보 DB에 추가
    @Transactional
    public void save(Files files){
        Files f = new Files();
        f.setFilename(files.getFilename());
        f.setOrigFilename(files.getOrigFilename());
        f.setFilePath(files.getFilePath());

        fileRepository.save(f);
    }


    // 이미지 파일 업로드 및 컨텐츠 내용 저장
    public Long uploadFile(MultipartFile picture, String content, PrincipalDetails principalDetails) throws IOException {
        // 이미지 파일 처리
        Files file = new Files();
        String originFileName = picture.getOriginalFilename();

        String originFileNameExtension = FilenameUtils.getExtension(originFileName).toLowerCase();

        FilenameUtils.removeExtension(originFileName);

        File storedFile;
        String storedFileName;

        String filePath = "/home/ubuntu/sparta/image/";
//        String filePath = "C:\\sparta\\image\\";

        do{
            storedFileName = RandomStringUtils.randomAlphanumeric(32) + "." + originFileNameExtension;
            storedFile = new File(filePath + storedFileName);
        }while(storedFile.exists());

        storedFile.getParentFile().mkdirs();
        picture.transferTo(storedFile);

        file.setFilename(storedFileName);
        file.setOrigFilename(originFileName);
        file.setFilePath(filePath);
        save(file);

        // 컨텐츠 내용 저장
        String username = principalDetails.getUsername();

        User user = userRepository.findByUsername(username);
        String nickname = user.getNickname();

        Board board = new Board(content,nickname,filePath+storedFileName);

        return boardRepository.save(board).getId();

    }

}
