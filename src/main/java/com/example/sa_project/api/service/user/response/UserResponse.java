package com.example.sa_project.api.service.user.response;

import com.example.sa_project.domain.user.Major;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
public class UserResponse {
    private Long id;
    private String name;
    private Major major;
    private String token;

}