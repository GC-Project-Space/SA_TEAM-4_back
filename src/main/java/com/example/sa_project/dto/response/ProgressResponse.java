package com.example.sa_project.dto.response;

import java.util.List;

import com.example.sa_project.dto.MissionProgress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressResponse {
    private String status;
    private List<MissionProgress> missions;
}
