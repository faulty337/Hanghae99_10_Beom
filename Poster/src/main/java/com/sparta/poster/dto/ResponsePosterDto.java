package com.sparta.poster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
}
