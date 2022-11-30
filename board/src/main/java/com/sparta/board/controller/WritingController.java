package com.sparta.board.controller;

import com.sparta.board.dto.WritingRequestDto;
import com.sparta.board.dto.WritingResponseDto;
import com.sparta.board.entity.ResponseMessage;
import com.sparta.board.entity.StatusEnum;
import com.sparta.board.service.WritingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.nio.charset.Charset;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class WritingController {

    private final WritingService writingService;

//    WritingController(WritingService writingService){
//        this.writingService = writingService;
//    }

    @GetMapping("/writing")
    public List<WritingResponseDto> getWritingList(){
        return writingService.getWritingList();
    }

    @GetMapping("/writing/{id}")
    public ResponseEntity<ResponseMessage> getWriting(@PathVariable Long id){
        ResponseMessage responseMessage = new ResponseMessage();
        WritingResponseDto writingResponseDto;

        writingResponseDto = writingService.getWriting(id); //아마 오류 발생

        responseMessage.setMessage("OK");
        responseMessage.setStatus(StatusEnum.OK);
        responseMessage.setData(writingResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/writing")
    public WritingResponseDto createWriting(@RequestBody WritingRequestDto writingRequestDto){
        return writingService.createWriting(writingRequestDto);
    }


    @PutMapping("/writing/{id}")
    public ResponseEntity<ResponseMessage> updateWriting(@PathVariable long id, @RequestBody WritingRequestDto writingRequestDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        WritingResponseDto writingResponseDto = writingService.updateWriting(id, writingRequestDto); //notfound

        ResponseMessage responseMessage = new ResponseMessage();

        responseMessage.setMessage("OK");
        responseMessage.setStatus(StatusEnum.OK);
        responseMessage.setData(writingResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
//
    @DeleteMapping("/writing/{id}")
    public ResponseEntity<ResponseMessage> deleteWriting(@PathVariable long id, @RequestParam String password){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseMessage responseMessage = new ResponseMessage();
        if(writingService.deleteWriting(id, password)){
            responseMessage.setStatus(StatusEnum.OK);
            responseMessage.setMessage("삭제 완료");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }else{
            responseMessage.setStatus(StatusEnum.BAD_REQUEST);
            responseMessage.setMessage("비밀번호 오류");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }

    }



}
