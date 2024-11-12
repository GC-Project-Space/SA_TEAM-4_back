package com.example.sa_project.api.service.mission;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.sa_project.api.service.mission.response.ClearResponse;
import com.example.sa_project.api.service.mission.response.ProgressResponse;
import com.example.sa_project.api.service.mission.response.ResetResponse;
import com.example.sa_project.api.service.mission.response.RewardResponse;
import com.example.sa_project.api.service.mission.response.mission.MissionProgress;
import com.example.sa_project.domain.mission.MyMission;
import com.example.sa_project.domain.mission.Mission;
import com.example.sa_project.domain.ranking.UserProgress;
import com.example.sa_project.domain.mission.repository.MyMissionRepository;
import com.example.sa_project.domain.mission.repository.MissionRepository;
import com.example.sa_project.domain.ranking.UserProgressRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {

    private final MyMissionRepository myMissionRepository;
    private final CompleteMissionRepository completeMissionRepository;
    private final MissionRepository missionRepository;
    private final UserProgressRepository uProgressRepository;

    public ProgressResponse getMissionProgress(Long userId) {
        List<MyMission> myMissions = myMissionRepository.findByUserId(userId);
        System.out.println("All Missions: " + myMissions);

        List<Integer> completedMissionIds = completeMissionRepository.findByUserId(userId).stream()
                .map(CompleteMission::getMissionId)
                .distinct()                      
                .collect(Collectors.toList());
    
        List<MissionProgress> missions = myMissions.stream()
                .map(myMission -> {
                    Mission mission = missionRepository.findById(myMission.getMissionId()).orElse(null);
    
                    boolean isCleared = completedMissionIds.contains(myMission.getMissionId());
    
                    return new MissionProgress(mission != null ? mission.getTitle() : "Unknown", myMission.getMissionId(), isCleared);
                })
                .collect(Collectors.toList());

        return new ProgressResponse("success", missions);
    }

    public ClearResponse clearMission(Long userId, int missionId){
        Mission mission = missionRepository.findById(missionId).orElse(null);

        if(mission == null){
            throw new IllegalArgumentException("Mission not found");
        }

        CompleteMission completeMission = new CompleteMission(userId, missionId);
        completeMissionRepository.save(completeMission);

        MissionProgress missionProgress = new MissionProgress(mission.getTitle(), missionId, true);

        return new ClearResponse("success", missionProgress);
    }

    public RewardResponse rewardUser(Long userId){
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

    public ResetResponse resetMissions(Long userId){
        List<MyMission> allUserMissions = myMissionRepository.findByUserId(userId);
        myMissionRepository.deleteAll(allUserMissions);

        List<CompleteMission> completeMissions = completeMissionRepository.findByUserId(userId);
        completeMissionRepository.deleteAll(completeMissions);

        List<Mission> allMissions = missionRepository.findAll();

        Collections.shuffle(allMissions);
        List<Mission> randomMissions = allMissions.stream().limit(3).collect(Collectors.toList());

        for (Mission mission : randomMissions){
            MyMission newMission = new MyMission(userId, mission.getMissionId());
            myMissionRepository.save(newMission);
        }

        return new ResetResponse("success");

    }

}
