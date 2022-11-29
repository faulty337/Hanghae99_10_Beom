package com.sparta.board.service;


import com.sparta.board.dto.WritingRequestDto;
import com.sparta.board.dto.WritingResponseDto;
import com.sparta.board.entity.ResponseMessage;
import com.sparta.board.entity.StatusEnum;
import com.sparta.board.entity.Writing;
import com.sparta.board.jpa.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingRepository writingRepository;

//   WritingService(WritingRepository writingRepository){
//       this.writingRepository = writingRepository;
//   }

    public List<WritingResponseDto> getWritingList(){
        List<Writing> writingList = writingRepository.findAll();
        List<WritingResponseDto> responseList = new ArrayList<>();
        for(Writing writing : writingList){
            responseList.add(new WritingResponseDto(writing));
        }
        return responseList;
    }

    public ResponseEntity<ResponseMessage> getWriting(Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseMessage responseMessage = new ResponseMessage();
        WritingResponseDto writingResponseDto = new WritingResponseDto();
        try{
            writingResponseDto = new WritingResponseDto(writingRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("인덱스 오류")));

        }catch (IllegalArgumentException e){
            responseMessage.setMessage(e+"");
            responseMessage.setStatus(StatusEnum.BAD_REQUEST);
            return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
        }
        responseMessage.setMessage("OK");
        responseMessage.setStatus(StatusEnum.OK);
        responseMessage.setData(writingResponseDto);
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    public WritingResponseDto createWriting(WritingRequestDto writingRequestDto){
        Writing writing = new Writing(writingRequestDto.getUsername(), writingRequestDto.getPassword(), writingRequestDto.getTitle(), writingRequestDto.getContent());
//        writingRepository.save(Writing.builder()
//                            .username(writingRequestDto.getUsername())
//                            .password(writingRequestDto.getPassword())
//                            .title(writingRequestDto.getTitle())
//                            .content(writingRequestDto.getContent())
//                            .build());
        writingRepository.save(writing);
        WritingResponseDto writingResponseDto = new WritingResponseDto(writing);
        return writingResponseDto;
    }

    public ResponseEntity<ResponseMessage> updateWriting(long id, WritingRequestDto writingRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseMessage responseMessage = new ResponseMessage();
        Writing writing = new Writing();

        try{
            writing = writingRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("인덱스 오류")
            );
        }catch (IllegalArgumentException e){
            responseMessage.setMessage(e.getMessage());
            responseMessage.setStatus(StatusEnum.BAD_REQUEST);
            return new ResponseEntity<>(responseMessage, headers, HttpStatus.BAD_REQUEST);
        }

        if(!writing.getPassword().equals(writingRequestDto.getPassword())){
            responseMessage.setMessage("비밀번호 오류");
            responseMessage.setStatus(StatusEnum.BAD_REQUEST);
            return new ResponseEntity<>(responseMessage, headers, HttpStatus.BAD_REQUEST);
        }

        writing.update(writingRequestDto.getTitle(), writingRequestDto.getContent()); //업데이트 부분..???
        writingRepository.save(writing);

        WritingResponseDto writingResponseDto = new WritingResponseDto(writing);

        responseMessage.setData(writingResponseDto);
        responseMessage.setStatus(StatusEnum.OK);
        responseMessage.setMessage("변경 완료");
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);




    }

    public ResponseEntity<ResponseMessage> deleteWriting(long id, String password){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseMessage responseMessage = new ResponseMessage();
        Writing writing = new Writing();
        try {
            writing = writingRepository.findById(id).orElseThrow(()->new IllegalArgumentException("인덱스 오류"));
        }catch (IllegalArgumentException e){
            responseMessage.setMessage(e.getMessage());
            responseMessage.setStatus(StatusEnum.BAD_REQUEST);
            return new ResponseEntity<>(responseMessage, headers, HttpStatus.BAD_REQUEST);
        }
        if(!writing.getPassword().equals(password)){
            responseMessage.setMessage("비밀번호 오류");
            responseMessage.setStatus(StatusEnum.BAD_REQUEST);
            return new ResponseEntity<>(responseMessage, headers, HttpStatus.BAD_REQUEST);
        }
        writingRepository.deleteById(id);
        responseMessage.setMessage("삭제 완료");
        responseMessage.setStatus(StatusEnum.OK);
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);

    }





}
