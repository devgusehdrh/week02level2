package com.sparta.week2level2.repository;

import com.sparta.week2level2.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByOrderByModifiedAtDesc();

}
