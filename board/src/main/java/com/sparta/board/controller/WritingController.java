package com.sparta.board.controller;

import com.sparta.board.dto.WritingRequestDto;
import com.sparta.board.dto.WritingResponseDto;
import com.sparta.board.entity.ResponseMessage;
import com.sparta.board.service.WritingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return writingService.getWriting(id);
    }

    @PostMapping("/writing")
    public WritingResponseDto createWriting(@RequestBody WritingRequestDto writingRequestDto){
        return writingService.createWriting(writingRequestDto);
    }


    @PutMapping("/writing/{id}")
    public ResponseEntity<ResponseMessage> updateWriting(@PathVariable long id, @RequestBody WritingRequestDto writingRequestDto){
        return writingService.updateWriting(id, writingRequestDto);
    }
//
    @DeleteMapping("/writing/{id}")
    public ResponseEntity<ResponseMessage> deleteWriting(@PathVariable long id, @RequestParam String password){
        return writingService.deleteWriting(id, password);
    }



}
