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
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Board post_id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    public Like(LikeRequestDto requestDto){
        this.post_id = requestDto.getPost_id();
        this.user_id = requestDto.getUser_id();
    }


//    public Like(Long post_id, Long id) {
//
//        board.getId() = post_id;
//        this.user_id = id;
//    }
}
