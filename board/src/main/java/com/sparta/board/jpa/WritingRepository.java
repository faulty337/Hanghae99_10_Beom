package com.sparta.board.jpa;

import com.sparta.board.entity.Writing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WritingRepository extends JpaRepository<Writing, Long> {
}
