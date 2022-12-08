package com.sparta.poster.service;


import com.sparta.poster.JwtUtil;
import com.sparta.poster.dto.RequestCommentDto;
import com.sparta.poster.entity.Comment;
import com.sparta.poster.entity.Poster;
import com.sparta.poster.entity.User;
import com.sparta.poster.entity.UserRoleEnum;
import com.sparta.poster.repostiory.CommentRepository;
import com.sparta.poster.repostiory.PosterRepository;
import com.sparta.poster.repostiory.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private final PosterRepository posterRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(PosterRepository posterRepository, UserRepository userRepository, JwtUtil jwtUtil, CommentRepository commentRepository) {
        this.posterRepository = posterRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public String createComment(RequestCommentDto requestCommentDto, String token){
        Claims tokenClaims = checkToken(token);
        System.out.println(requestCommentDto.getPosterId() + " " + requestCommentDto.getContent());
        User user = userRepository.findByUsername(tokenClaims.get("username").toString()).orElseThrow(
                () -> new IllegalArgumentException("잘못된 로그인입니다.")
        );

        Poster poster = posterRepository.findById(requestCommentDto.getPosterId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );

        Comment comment = commentRepository.save(Comment.builder()
                .user(user)
                .poster(poster)
                .content(requestCommentDto.getContent())
                .build());
        poster.addComment(comment);
        return comment.getContent();
    }


    @Transactional
    public String updateComment(RequestCommentDto requestCommentDto, String token){
        Claims tokenClaims = checkToken(token);
        Comment comment = commentRepository.findById(requestCommentDto.getCommentId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        User user = userRepository.findByUsername(tokenClaims.get("username").toString()).orElseThrow(
                () -> new IllegalArgumentException("잘못된 계정입니다.")
        );
        if(!comment.getUser().getId().equals(user.getId()) && !tokenClaims.get("adminRole").equals(UserRoleEnum.Admin.toString())){
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        comment.update(requestCommentDto.getContent());
        return comment.getContent();
    }
    @Transactional
    public boolean deleteComment(RequestCommentDto requestCommentDto, String token){
        Long commentId = requestCommentDto.getCommentId();
        Claims tokenClaims = checkToken(token);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        User user = userRepository.findByUsername(tokenClaims.get("username").toString()).orElseThrow(
                () -> new IllegalArgumentException("잘못된 계정입니다.")
        );
        if(!comment.getUser().getId().equals(user.getId()) && !tokenClaims.get("adminRole").equals(UserRoleEnum.Admin.toString())){
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        commentRepository.deleteById(commentId);
        return true;
    }

    private Claims checkToken(String token) {
        try{
            return jwtUtil.getUserInfoFromToken(token);
        }catch (Exception e){
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }
    }
}
