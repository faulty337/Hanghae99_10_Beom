package com.sparta.poster.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestCommentDto {
    private Long posterId = 0L;
    private Long commentId = 0L;
    private String content = "";
}
