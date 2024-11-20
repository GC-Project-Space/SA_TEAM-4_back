package com.example.sa_project.api.service.user;

import com.example.sa_project.api.controller.user.request.UserRequest;
import com.example.sa_project.api.service.user.response.UserResponseNoToken;
import com.example.sa_project.api.service.user.response.UserResponse;
import com.example.sa_project.domain.user.Member;
import com.example.sa_project.domain.user.MemberRepository;
import com.example.sa_project.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UserResponseNoToken signUp(UserRequest userRequest) {

        // 이미 존재하는 사용자 이름 체크
        if (memberRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }

        Member member = new Member();
        member.setUsername(userRequest.getUsername());
        member.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        member.setMajor(userRequest.getMajor());
        member.setRole("User");

        return entityToSignDto(memberRepository.save(member));
    }

    public UserResponse login(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();

        Member member = memberRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(userRequest.getPassword(), member.getPassword())) {
            String token = jwtUtil.generateToken(member.getUsername());
            userResponse.setToken(token);
        }

        userResponse.setId(member.getId());
        userResponse.setName(member.getUsername());
        userResponse.setMajor(member.getMajor());

        return userResponse;
    }


    public UserResponseNoToken findByID(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return entityToSignDto(member);
    }

    private UserResponse entityToDto(Member member) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(member.getId());
        userResponse.setName(member.getUsername());
        userResponse.setMajor(member.getMajor());
        return userResponse;
    }

    private UserResponseNoToken entityToSignDto(Member member) {
        UserResponseNoToken userResponseNoToken = new UserResponseNoToken();
        userResponseNoToken.setId(member.getId());
        userResponseNoToken.setName(member.getUsername());
        userResponseNoToken.setMajor(member.getMajor());
        return userResponseNoToken;
    }
}
