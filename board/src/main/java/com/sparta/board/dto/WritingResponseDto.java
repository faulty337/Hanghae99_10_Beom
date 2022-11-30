package com.sparta.board.dto;

import com.sparta.board.entity.Writing;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class WritingResponseDto {
    Long id;
    String title;
    String username;
    String content;
    LocalDateTime createAt;


}
