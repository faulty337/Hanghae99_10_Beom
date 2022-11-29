package com.sparta.board.dto;


import com.sparta.board.entity.Writing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WritingRequestDto {
    String title;
    String username;
    String content;
    String password;
}