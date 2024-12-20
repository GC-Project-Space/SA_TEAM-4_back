package com.example.sa_project.api.controller.home;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final Environment environment;

    @GetMapping("/")
    public String getVersion() {
        return "가마고치 서버입니다.";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return Arrays.stream(environment.getActiveProfiles()).findFirst().orElse("default");
    }
}
