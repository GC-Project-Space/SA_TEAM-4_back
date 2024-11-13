package com.example.sa_project.Ranking;

import lombok.Data;

@Data
public class UserProgressDTO {
    private String name; // user name
    private long experiencePoints;

    // 생성자에 major 필드 추가
    public UserProgressDTO(String name, long experiencePoints) {
        this.name = name;
        this.experiencePoints = experiencePoints;
    }
}
