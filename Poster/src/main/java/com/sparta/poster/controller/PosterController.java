package com.sparta.poster.controller;


import com.sparta.poster.dto.RequestPosterDto;
import com.sparta.poster.dto.ResponseMessage;
import com.sparta.poster.dto.UpdatePosterDto;
import com.sparta.poster.entity.StatusEnum;
import com.sparta.poster.service.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/poster")
public class PosterController {

    private final PosterService posterService;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    PosterController(PosterService posterService){
        this.posterService = posterService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> getPosterList(){
        ResponseMessage responseMessage = new ResponseMessage(StatusEnum.OK, "조회 성공", posterService.getPosterList());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
//    @GetMapping("/test")
//    public String getTest(
//            @CookieValue(name = "Authorization", defaultValue = "") String token){
//        return "token : " + token;
//    }

//    @PostMapping("/")
//    public ResponseEntity<ResponseMessage> createPoster(
//            @RequestBody RequestPosterDto requestPosterDto,
//            @CookieValue(name = "Authorization", defaultValue = "") String token){
//        return null;
//    }
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createPoster(
            @RequestBody RequestPosterDto requestPosterDto,
            HttpServletRequest request,
            @CookieValue(name = AUTHORIZATION_HEADER) String token){
        //만약 쿠키에 있는 값 그대로 사용할 경우 token에 있는 값 사용하면 됨

        //일단 지금은 헤더 연습
        ResponseMessage responseMessage = new ResponseMessage(
                StatusEnum.OK,
                "작성 완료",
                posterService.createPoster(requestPosterDto,token));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getPoster(@PathVariable Long id){
        ResponseMessage responseMessage = new ResponseMessage(StatusEnum.OK, "조회 성공", posterService.getPoster(id));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updatePoster(
            @PathVariable Long id,
            @RequestBody UpdatePosterDto updatePosterDto,
            @CookieValue(name = "Authorization", defaultValue = "") String token){
        ResponseMessage responseMessage = new ResponseMessage(StatusEnum.OK, "변경 성공", posterService.updatePoster(updatePosterDto, id, token));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deletePoster(
            @PathVariable Long id,
            @CookieValue(name = "Authorization", defaultValue = "") String token){
        ResponseMessage responseMessage = new ResponseMessage(StatusEnum.OK, "삭제 성공", posterService.deletePoster(id, token));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
