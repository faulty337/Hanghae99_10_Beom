package com.sparta.poster.dto;

import com.sparta.poster.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ResponseCommentDto {
    private Long id;
    private String username;
    private String content;
    private LocalDateTime createAt;

    public ResponseCommentDto(Comment comment){
        this.id = comment.getId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
    }
}
