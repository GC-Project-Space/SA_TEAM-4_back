package com.example.sa_project.api.service.mission;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sa_project.api.service.mission.response.mission.MissionProgress;
import com.example.sa_project.api.service.mission.response.ClearResponse;
import com.example.sa_project.api.service.mission.response.ProgressResponse;
import com.example.sa_project.api.service.mission.response.RewardResponse;
import com.example.sa_project.domain.mission.AllMission;
import com.example.sa_project.domain.mission.CompleteMission;
import com.example.sa_project.domain.mission.Mission;
import com.example.sa_project.domain.mission.UserProgress;
import com.example.sa_project.domain.mission.repository.AllMissionRepository;
import com.example.sa_project.domain.mission.repository.CompleteMissionRepository;
import com.example.sa_project.domain.mission.repository.MissionRepository;
import com.example.sa_project.domain.mission.repository.UserProgressRepository;

@Service
public class MissionService {

    @Autowired
    private AllMissionRepository allMissionRepository;

    @Autowired
    private CompleteMissionRepository completeMissionRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserProgressRepository uProgressRepository;

    public ProgressResponse getMissionProgress(String userId) {
        List<AllMission> allMissions = allMissionRepository.findByUserId(userId);
        System.out.println("All Missions: " + allMissions);

        List<Integer> completedMissionIds = completeMissionRepository.findByUserId(userId).stream()
                .map(CompleteMission::getMissionId)
                .distinct()                      
                .collect(Collectors.toList());
    
        List<MissionProgress> missions = allMissions.stream()
                .map(allMission -> {
                    Mission mission = missionRepository.findById(allMission.getMissionId()).orElse(null);
    
                    boolean isCleared = completedMissionIds.contains(allMission.getMissionId());
    
                    return new MissionProgress(mission != null ? mission.getTitle() : "Unknown", allMission.getMissionId(), isCleared);
                })
                .collect(Collectors.toList());

        return new ProgressResponse("success", missions);
    }

    public ClearResponse clearMission(String userId, int missionId){
        Mission mission = missionRepository.findById(missionId).orElse(null);

        if(mission == null){
            throw new IllegalArgumentException("Mission not found");
        }

        CompleteMission completeMission = new CompleteMission(userId, missionId);
        completeMissionRepository.save(completeMission);

        MissionProgress missionProgress = new MissionProgress(mission.getTitle(), missionId, true);

        return new ClearResponse("success", missionProgress);
    }

    public RewardResponse rewardUser(String userId){
        UserProgress userProgress = uProgressRepository.findById(userId).orElse(null);

        if(userProgress == null){
            throw new IllegalArgumentException("User not found");
        }

        int reward = 100;

        userProgress.setExperiencePoint(userProgress.getExperiencePoint() + reward);

        int LevelUp = 0;
        if(userProgress.getExperiencePoint() >= 1000){
            LevelUp = userProgress.getExperiencePoint() / 1000;
            userProgress.setExperiencePoint(userProgress.getExperiencePoint() - (LevelUp * 1000));
            userProgress.setLevel(userProgress.getLevel() + LevelUp);
        }

        uProgressRepository.save(userProgress);

        RewardResponse rewardResponse = new RewardResponse("success", userProgress.getLevel(), userProgress.getExperiencePoint());

        return rewardResponse;
    }
}
