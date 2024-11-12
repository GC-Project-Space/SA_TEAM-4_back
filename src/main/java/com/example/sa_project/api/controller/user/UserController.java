package com.example.sa_project.api.controller.user;

import com.example.sa_project.api.controller.response.ErrorResponse;
import com.example.sa_project.api.controller.user.request.UserRequest;
import com.example.sa_project.api.service.user.UserService;
import com.example.sa_project.api.service.user.response.UserResponseNoToken;
import com.example.sa_project.api.service.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserRequest request) {
        try {
            UserResponseNoToken userResponseNoToken = userService.signUp(request);
            return ResponseEntity.ok(userResponseNoToken);
        } catch (IllegalArgumentException e) {
            // ErrorResponse로 에러 메시지 전달
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request) {
        UserResponse login = userService.login(request);
        return ResponseEntity.ok(login);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseNoToken> getUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.findByID(userId));
    }

}