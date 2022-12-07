package com.sparta.poster.service;

import com.sparta.poster.JwtUtil;
import com.sparta.poster.dto.RequestUserDto;
import com.sparta.poster.entity.User;
import com.sparta.poster.repostiory.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwt;

    UserService(UserRepository usersRepository, JwtUtil jwt){
        this.userRepository = usersRepository;
        this.jwt = jwt;
    }

    public String signup(RequestUserDto requestUserDto){
        String username = requestUserDto.getUsername();
        String password = requestUserDto.getPassword();

        if(userRepository.findByUsername(username).isEmpty()){
            User user = new User(username, password);
            userRepository.save(user);
            return "회원 가입 완료";
        }else{
            throw new IllegalArgumentException("중복되는 아이디가 존재합니다.");
        }
    }

    public String login(RequestUserDto requestUserDto){
        String username = requestUserDto.getUsername();
        String password = requestUserDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("틀린 계정입니다.")
        );

        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("틀린 계정입니다.");
        }

        return jwt.createToken(user.getUsername(), user.getId());
    }
}
