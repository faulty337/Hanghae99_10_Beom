package com.sparta.poster.controller;

import com.sparta.poster.dto.RequestCommentDto;
import com.sparta.poster.dto.ResponseMessage;
import com.sparta.poster.entity.StatusEnum;
import com.sparta.poster.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createComment(
            @RequestBody RequestCommentDto requestCommentDto,
            @CookieValue(name = AUTHORIZATION_HEADER) String token
    ){


        ResponseMessage responseMessage = new ResponseMessage(StatusEnum.OK, "작성 완료",commentService.createComment(requestCommentDto, token));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> updateComment(
            @RequestBody RequestCommentDto requestCommentDto,
            @CookieValue(name = AUTHORIZATION_HEADER) String token
    ){
        ResponseMessage responseMessage = new ResponseMessage(StatusEnum.OK, "변경 완료",commentService.updateComment(requestCommentDto, token));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessage> deleteComment(
            @RequestBody RequestCommentDto requestCommentDto,
            @CookieValue(name = AUTHORIZATION_HEADER) String token
    ){
        ResponseMessage responseMessage = new ResponseMessage(StatusEnum.OK, "삭제 완료",commentService.deleteComment(requestCommentDto, token));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
