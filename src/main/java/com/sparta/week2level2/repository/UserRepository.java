package com.sparta.week2level2.repository;

import com.sparta.week2level2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);


}
