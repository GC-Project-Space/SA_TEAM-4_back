package com.example.sa_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MissionProgress {
    private String missionName;
    private int missionId;
    private boolean isCleared;
}
