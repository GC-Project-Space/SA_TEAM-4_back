package com.example.sa_project.dto.response;

import com.example.sa_project.dto.MissionProgress;

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
