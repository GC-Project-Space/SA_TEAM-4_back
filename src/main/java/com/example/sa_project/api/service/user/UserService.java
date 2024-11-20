package com.example.sa_project.api.service.user;

import com.example.sa_project.api.controller.user.request.UserRequest;
import com.example.sa_project.api.service.user.response.UserResponseNoToken;
import com.example.sa_project.api.service.user.response.UserResponse;
import com.example.sa_project.domain.user.User;
import com.example.sa_project.domain.user.UserRepository;
import com.example.sa_project.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UserResponseNoToken signUp(UserRequest userRequest) {

        // 이미 존재하는 사용자 이름 체크
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setMajor(userRequest.getMajor());
        user.setRole("User");

        return entityToSignDto(userRepository.save(user));
    }

    public UserResponse login(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();

        User user = userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            userResponse.setToken(token);
        }

        userResponse.setId(user.getId());
        userResponse.setName(user.getUsername());
        userResponse.setMajor(user.getMajor());

        return userResponse;
    }


    public UserResponseNoToken findByID(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return entityToSignDto(user);
    }

    private UserResponse entityToDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getUsername());
        userResponse.setMajor(user.getMajor());
        return userResponse;
    }

    private UserResponseNoToken entityToSignDto(User user) {
        UserResponseNoToken userResponseNoToken = new UserResponseNoToken();
        userResponseNoToken.setId(user.getId());
        userResponseNoToken.setName(user.getUsername());
        userResponseNoToken.setMajor(user.getMajor());
        return userResponseNoToken;
    }
}
