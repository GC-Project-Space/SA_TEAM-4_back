package com.example.sa_project.api.service.mission.response.mission;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MissionProgressResponse {
    private String status;
    private List<MissionProgress> missions;
    private int level;
    private int experiencePoint;
}
