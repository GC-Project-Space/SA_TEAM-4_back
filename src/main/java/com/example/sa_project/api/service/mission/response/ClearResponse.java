package com.example.sa_project.api.service.mission.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClearResponse {
    private String status;
    private MissionProgress missions;
}
