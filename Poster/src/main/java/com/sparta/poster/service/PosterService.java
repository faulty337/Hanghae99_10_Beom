package com.sparta.poster.service;

import com.sparta.poster.JwtUtil;
import com.sparta.poster.dto.RequestPosterDto;
import com.sparta.poster.dto.ResponsePosterDto;
import com.sparta.poster.dto.UpdatePosterDto;
import com.sparta.poster.entity.Poster;
import com.sparta.poster.repostiory.PosterRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PosterService {
    private final JwtUtil jwtUtil;
    private final PosterRepository posterRepository;

    @Autowired
    PosterService(PosterRepository posterRepository, JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.posterRepository = posterRepository;
    }

    @Transactional(readOnly = true)
    public List<ResponsePosterDto> getPosterList(){
        List<ResponsePosterDto> responsePosterDtoList = new ArrayList<>();
        List<Poster> posterList = posterRepository.findAll();

        for(Poster poster : posterList){
            responsePosterDtoList.add(ResponsePosterDto.builder()
                            .posterId(poster.getId())
                            .title(poster.getTitle())
                            .content(poster.getContent())
                            .username(poster.getUsername())
                            .createAt(poster.getCreatedAt()).build());
        }
        return responsePosterDtoList;
    }

    @Transactional
    public ResponsePosterDto createPoster(RequestPosterDto requestPosterDto, String token){
        Claims tokenClaims;
        try{
            tokenClaims =  jwtUtil.getUserInfoFromToken(token);
        }catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }

        Poster poster = posterRepository.save(Poster.builder()
                .title(requestPosterDto.getTitle())
                .content(requestPosterDto.getContent())
                .username(tokenClaims.getSubject())
                .build());

        return ResponsePosterDto.builder()
                .posterId(poster.getId())
                .title(poster.getTitle())
                .content(poster.getContent())
                .username(poster.getUsername())
                .createAt(poster.getCreatedAt()).build();
    }

    @Transactional(readOnly = true)
    public ResponsePosterDto getPoster(Long posterId){
        Poster poster = posterRepository.findById(posterId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 id입니다.")
        );
        return ResponsePosterDto.builder()
                .posterId(poster.getId())
                .title(poster.getTitle())
                .content(poster.getContent())
                .username(poster.getUsername())
                .createAt(poster.getCreatedAt()).build();

    }

    @Transactional
    public ResponsePosterDto updatePoster(UpdatePosterDto updatePosterDto, Long posterId, String token){
        Claims tokenClaims;
        try{
            tokenClaims =  jwtUtil.getUserInfoFromToken(token);
        }catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }
        Poster poster = posterRepository.findById(posterId).orElseThrow(
                ()-> new IllegalArgumentException("잘못된 게시물 입니다.")
        );

        if(!poster.getUsername().equals(tokenClaims.getSubject())){
            throw new IllegalArgumentException("해당 게시물에 권한이 없습니다.");
        }

        poster.update(updatePosterDto.getTitle(), updatePosterDto.getContent());

        return ResponsePosterDto.builder()
                .posterId(poster.getId())
                .title(poster.getTitle())
                .content(poster.getContent())
                .username(poster.getUsername())
                .createAt(poster.getCreatedAt()).build();
    }
    @Transactional
    public Long deletePoster(Long posterId, String token){
        Claims tokenClaims;
        try{
            tokenClaims =  jwtUtil.getUserInfoFromToken(token);
        }catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }
        Poster poster = posterRepository.findById(posterId).orElseThrow(
                ()-> new IllegalArgumentException("잘못된 게시물 입니다.")
        );
        if(!poster.getUsername().equals(tokenClaims.getSubject())){
            throw new IllegalArgumentException("해당 게시물에 권한이 없습니다.");
        }
        posterRepository.deleteById(posterId);
        return posterId;
    }

}
