package com.sparta.poster.repostiory;

import com.sparta.poster.entity.Poster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosterRepository extends JpaRepository<Poster, Long> {
}
