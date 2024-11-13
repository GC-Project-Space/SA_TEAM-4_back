package com.example.sa_project.Ranking;

import lombok.Data;

@Data
public class MajorProgressDTO {
    private Major major;
    private Long experiencePointsSum;

    public MajorProgressDTO(Major major, Long experiencePoints) {
        this.major = major;
        this.experiencePointsSum = experiencePoints;
    }
}

