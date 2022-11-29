package com.sparta.board.entity;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    private StatusEnum status;
    private String message;
    private Object data;

}
