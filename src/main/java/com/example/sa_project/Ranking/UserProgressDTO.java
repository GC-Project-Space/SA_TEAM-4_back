package com.example.sa_project.Ranking;

import lombok.Data;

@Data
public class UserProgressDTO {
    private String name; // user name
    private Long experiencePoints;

    // 생성자에 major 필드 추가
    public UserProgressDTO(String username, Long experiencePoint) {
        this.name = username;
        this.experiencePoints = experiencePoint;
    }
}
