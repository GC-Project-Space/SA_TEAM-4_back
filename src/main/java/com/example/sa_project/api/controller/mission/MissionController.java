package com.example.sa_project.api.controller.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sa_project.api.controller.mission.request.ClearRequest;
import com.example.sa_project.api.controller.mission.request.ProgressRequest;
import com.example.sa_project.api.controller.mission.request.ResetRequest;
import com.example.sa_project.api.controller.mission.request.RewardRequest;
import com.example.sa_project.api.service.mission.MissionService;
import com.example.sa_project.api.service.mission.response.ClearResponse;
import com.example.sa_project.api.service.mission.response.ProgressResponse;
import com.example.sa_project.api.service.mission.response.ResetResponse;
import com.example.sa_project.api.service.mission.response.RewardResponse;



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

    @PostMapping("/reset")
    public ResetResponse postMethodName(@RequestBody ResetRequest request) {
        return missionService.resetMissions(request.getUserId());
    }
    
    
    
    
}
