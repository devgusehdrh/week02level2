package com.sparta.week2level2.model;

import com.sparta.week2level2.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Board extends Timestamp{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private Long id;

    @Column(name = "content")
    private String content;
    @Column(name = "picture")
    private String picture;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "likecount")
    private Long likeCount;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user_id;

    @Column
    private Long fileId;

    public Board(String content, String nickname, String picture){
        this.content = content;
        this.nickname = nickname;
        this.picture = picture;
    }

    public void update(BoardRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.picture = requestDto.getPicture();
    }
}
