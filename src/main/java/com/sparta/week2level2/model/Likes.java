package com.sparta.week2level2.model;

import com.sparta.week2level2.dto.LikeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
// table"like"는 MySQL에서 이미 예약어로 존재하는 단어라서 테이블 생성시 오류가 발생한다.
// 참고자료: https://lktgt.tistory.com/47
@Entity(name="likes")
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Board postid;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userid;

    public Likes(LikeRequestDto requestDto){
        this.postid = requestDto.getPost_id();
        this.userid = requestDto.getUser_id();
    }


    public Likes(Board post_id, User user_id) {

        this.postid = post_id;
        this.userid = user_id;
    }
}
