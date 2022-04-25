package com.sparta.week2level2.repository;

import com.sparta.week2level2.model.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<Files, Long> {
    Optional<Files> findById(Long id);
}
