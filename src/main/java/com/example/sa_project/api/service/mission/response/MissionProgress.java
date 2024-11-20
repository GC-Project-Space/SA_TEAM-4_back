package com.example.sa_project.api.service.mission.response;

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
