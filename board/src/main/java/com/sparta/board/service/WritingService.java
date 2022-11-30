package com.sparta.board.service;


import com.sparta.board.Exception.NotFoundExtion;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<WritingResponseDto> getWritingList(){
        List<Writing> writingList = writingRepository.findAll();
        List<WritingResponseDto> responseList = new ArrayList<>();
        for(Writing writing : writingList){
            responseList.add(new WritingResponseDto());
        }
        return responseList;
    }

    @Transactional
    public WritingResponseDto getWriting(Long id){
        Writing writing = writingRepository.findById(id).orElseThrow(()-> new NotFoundExtion("인덱스 오류"));
        WritingResponseDto writingResponseDto = new WritingResponseDto();
        //이건 객체지향이 아니다 NotFound라는 에러는 내가 커스텀한 에러이기 때문
        return writingResponseDto;
    }

    @Transactional
    public WritingResponseDto createWriting(WritingRequestDto writingRequestDto){
        Writing writing = new Writing(writingRequestDto.getUsername(), writingRequestDto.getPassword(), writingRequestDto.getTitle(), writingRequestDto.getContent());
        writingRepository.save(writing);
        WritingResponseDto writingResponseDto = new WritingResponseDto(writing.getId(), writing.getTitle(),writing.getTitle(), writing.getContent(), writing.getCreatedAt());
        return writingResponseDto;
    }

    @Transactional
    public WritingResponseDto updateWriting(long id, WritingRequestDto writingRequestDto) {

        Writing writing = writingRepository.findById(id).orElseThrow(()-> new NotFoundExtion("인덱스 오류"));

        writing.update(writingRequestDto.getTitle(), writingRequestDto.getContent()); //업데이트 부분..???
//        writingRepository.save(writing);

        WritingResponseDto writingResponseDto = new WritingResponseDto(writing.getId(), writing.getTitle(),writing.getTitle(), writing.getContent(), writing.getCreatedAt());
        return writingResponseDto;




    }

    @Transactional
    public Boolean deleteWriting(long id, String password){

        Writing writing = writingRepository.findById(id).orElseThrow(()->new NotFoundExtion("인덱스 오류"));

        if(!writing.getPassword().equals(password)){
            return false;
        }
        writingRepository.deleteById(id);

        return true;

    }

    public ResponseEntity<ResponseMessage> makeResponse(){
        return null;
    }




}
