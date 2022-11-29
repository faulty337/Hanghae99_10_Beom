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

    public WritingResponseDto(Writing writing) {
        this.id = writing.getId();
        this.title = writing.getTitle();
        this.username = writing.getUsername();
        this.content = writing.getContent();
        this.createAt = writing.getCreatedAt();
    }

}
