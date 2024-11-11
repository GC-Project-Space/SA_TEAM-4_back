package com.example.sa_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sa_project.dto.request.ClearRequest;
import com.example.sa_project.dto.request.ProgressRequest;
import com.example.sa_project.dto.request.RewardRequest;
import com.example.sa_project.dto.response.ClearResponse;
import com.example.sa_project.dto.response.ProgressResponse;
import com.example.sa_project.dto.response.RewardResponse;
import com.example.sa_project.service.MissionService;



@RestController
@RequestMapping("/mission")
public class MissionController {
    @Autowired
    private MissionService missionService;

    @GetMapping("/progress")
    public ProgressResponse getMethodName(@RequestBody ProgressRequest request) {
        return missionService.getMissionProgress(request.getUserId());
    }

    @PostMapping("/clear")
    public ClearResponse getMethodName(@RequestBody ClearRequest request) {
        return missionService.clearMission(request.getUserId(), request.getMissionId());
    }

    @PostMapping("/reward")
    public RewardResponse getMethodName(@RequestBody RewardRequest request) {
        return missionService.rewardUser(request.getUserId());
    }
    
    
    
}
