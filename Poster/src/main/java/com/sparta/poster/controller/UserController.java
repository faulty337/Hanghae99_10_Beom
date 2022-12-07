package com.sparta.poster.controller;


import com.sparta.poster.dto.RequestUserDto;
import com.sparta.poster.dto.ResponseMessage;
import com.sparta.poster.entity.StatusEnum;
import com.sparta.poster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    UserController(UserService userService){
        this.userService = userService;

    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> signup(@RequestBody RequestUserDto requestUserDto){
        userService.signup(requestUserDto);
        ResponseMessage responseMessage = new ResponseMessage(StatusEnum.OK, "회원 가입 성공", null);//나중에 토큰 넣기
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody RequestUserDto requestUserDto, HttpServletResponse response){
        String token =  userService.login(requestUserDto);
        ResponseMessage responseMessage = new ResponseMessage(StatusEnum.OK,token, null);

        //만약 쿠키 사용할 경우
        Cookie cookie = new Cookie("Authorization", token.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        //헤더 사용할 경우
        response.addHeader(AUTHORIZATION_HEADER, token);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String getTest(@CookieValue(name = "Authorization", defaultValue = "") String token){
        return "token : " + token;
    }
}
