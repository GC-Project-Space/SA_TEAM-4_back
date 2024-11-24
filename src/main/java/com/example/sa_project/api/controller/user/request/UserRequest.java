package com.example.sa_project.api.controller.user.request;

import com.example.sa_project.domain.user.Major;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserRequest {

    private String username;
    private String password;
    private Major major;
}