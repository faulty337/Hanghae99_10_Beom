package com.sparta.poster.service;

import com.sparta.poster.JwtUtil;
import com.sparta.poster.dto.RequestPosterDto;
import com.sparta.poster.dto.ResponseCommentDto;
import com.sparta.poster.dto.ResponsePosterDto;
import com.sparta.poster.dto.UpdatePosterDto;
import com.sparta.poster.entity.Comment;
import com.sparta.poster.entity.Poster;
import com.sparta.poster.entity.UserRoleEnum;
import com.sparta.poster.repostiory.PosterRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            responsePosterDtoList.add(new ResponsePosterDto(poster));
        }
        return responsePosterDtoList;
    }

    @Transactional
    public ResponsePosterDto createPoster(RequestPosterDto requestPosterDto, String token){
        Claims tokenClaims = checkToken(token);
        Poster poster = posterRepository.save(Poster.builder()
                .title(requestPosterDto.getTitle())
                .content(requestPosterDto.getContent())
                .commentList(new ArrayList<>())
                .username(tokenClaims.get("username").toString())
                .build());

        return new ResponsePosterDto(poster);
    }

    @Transactional(readOnly = true)
    public ResponsePosterDto getPoster(Long posterId){
        Poster poster = posterRepository.findById(posterId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 id입니다.")
        );
        return new ResponsePosterDto(poster);

    }

    @Transactional
    public ResponsePosterDto updatePoster(UpdatePosterDto updatePosterDto, Long posterId, String token){
        Claims tokenClaims = checkToken(token);
        Poster poster = posterRepository.findById(posterId).orElseThrow(
                ()-> new IllegalArgumentException("잘못된 게시물 입니다.")
        );
//        System.out.println(tokenClaims.get("username"));
//        System.out.println(tokenClaims.get("auth"));
        System.out.println(tokenClaims.get("adminRole").getClass() + " " + UserRoleEnum.Admin.getClass());
        if(!poster.getUsername().equals(tokenClaims.get("username").toString()) && !tokenClaims.get("adminRole").equals(UserRoleEnum.Admin.toString())){
            throw new IllegalArgumentException("해당 게시물에 권한이 없습니다.");
        }

        poster.update(updatePosterDto.getTitle(), updatePosterDto.getContent());

        return new ResponsePosterDto(poster);
    }
    @Transactional
    public Long deletePoster(Long posterId, String token){
        Claims tokenClaims = checkToken(token);
        Poster poster = posterRepository.findById(posterId).orElseThrow(
                ()-> new IllegalArgumentException("잘못된 게시물 입니다.")
        );
        if(!poster.getUsername().equals(tokenClaims.get("username").toString()) && !tokenClaims.get("adminRole").equals(UserRoleEnum.Admin.toString())){
            throw new IllegalArgumentException("해당 게시물에 권한이 없습니다.");
        }
        posterRepository.deleteById(posterId);
        return posterId;
    }

    private Claims checkToken(String token){
        try{
            return jwtUtil.getUserInfoFromToken(token);
        }catch (Exception e){
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }
    }

}
