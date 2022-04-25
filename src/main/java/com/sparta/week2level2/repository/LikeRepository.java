package com.sparta.week2level2.repository;

import com.sparta.week2level2.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
