package com.sparta.poster.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class RequestPosterDto {
    private String title;
    private String username;
    private String content;
}
