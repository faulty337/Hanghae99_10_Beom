package com.sparta.poster.dto;

import com.sparta.poster.entity.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMessage {
    private StatusEnum status;
    private String message;
    private Object data;


}
