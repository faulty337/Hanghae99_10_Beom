package com.sparta.poster.dto;

import com.sparta.poster.entity.Comment;
import com.sparta.poster.entity.Poster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponsePosterDto {
    private Long posterId;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createAt;
    private List<ResponseCommentDto> commentList = new ArrayList<>();

    public ResponsePosterDto(Poster poster) {
        this.posterId = poster.getId();
        this.title = poster.getTitle();
        this.username = poster.getUsername();
        this.content = poster.getContent();
        this.createAt = poster.getCreatedAt();
        this.commentList = poster.getCommentList().stream().map(ResponseCommentDto::new).collect(Collectors.toList());
    }
}
