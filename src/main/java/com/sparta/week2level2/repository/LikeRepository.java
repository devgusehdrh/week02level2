package com.sparta.week2level2.repository;

import com.sparta.week2level2.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {
    boolean findByUseridAndPostid(Long user_id, Long post_id);
}
