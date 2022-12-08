package com.sparta.poster.service;

import com.sparta.poster.JwtUtil;
import com.sparta.poster.dto.RequestUserDto;
import com.sparta.poster.entity.User;
import com.sparta.poster.entity.UserRoleEnum;
import com.sparta.poster.repostiory.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwt;

    @Value("${admin.code}")
    private String ADMIN_CODE;

    UserService(UserRepository usersRepository, JwtUtil jwt){
        this.userRepository = usersRepository;
        this.jwt = jwt;
    }

    public String signup(RequestUserDto requestUserDto){
        String username = requestUserDto.getUsername();
        String password = requestUserDto.getPassword();
        UserRoleEnum role = UserRoleEnum.User;
        if(requestUserDto.isAdmin()){
            if(requestUserDto.getAdminCode().equals(ADMIN_CODE)){
                role = UserRoleEnum.Admin;
            }else{
                throw new IllegalArgumentException("틀린 어드민 코드 입니다.");
            }
        }
        if(userRepository.findByUsername(username).isEmpty()){
            User user = new User(username, password, role);
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

        return jwt.createToken(user.getUsername(), user.getRole());
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
