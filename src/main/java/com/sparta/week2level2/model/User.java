package com.sparta.week2level2.model;



import com.sparta.week2level2.dto.UserRequestDto;
import com.sparta.week2level2.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToMany(mappedBy = "user_id")
    @Column(nullable = true,name = "user_id")
    private Long id;

    @Column(nullable = false,name = "username")
    private String username;

    @Column(nullable = false,name = "pw")
    private String pw;

    @Column(nullable = false,name = "nickname")
    private String nickname;


    public User(String username, String pw, String nickname) {
        this.username = username;
        this.pw = pw;
        this.nickname = nickname;
    }

    public void User(UserRequestDto requestDto){
        this.id = requestDto.getId();
        this.pw = requestDto.getPw();
        this.nickname = requestDto.getNickname();
    }
}