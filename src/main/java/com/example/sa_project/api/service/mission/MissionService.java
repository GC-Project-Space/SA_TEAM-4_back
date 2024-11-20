package com.example.sa_project.api.service.mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.sa_project.api.service.mission.response.ClearResponse;
import com.example.sa_project.api.service.mission.response.MissionProgress;
import com.example.sa_project.api.service.mission.response.ProgressResponse;
import com.example.sa_project.api.service.mission.response.ResetResponse;
import com.example.sa_project.domain.mission.MyMission;
import com.example.sa_project.domain.mission.Mission;
import com.example.sa_project.domain.mission.MyMissionRepository;
import com.example.sa_project.domain.mission.MissionRepository;
import com.example.sa_project.domain.user.User;
import com.example.sa_project.domain.user.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {

    private final MyMissionRepository myMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public ProgressResponse getMissionProgress(Long userId) {
        List<MyMission> myMissions = myMissionRepository.findByUserId(userId);

        List<MissionProgress> missionProgresses = new ArrayList<>();

        for(MyMission mission : myMissions){
            boolean isCompleted = mission.isCompleted();
            Mission missionEntity = mission.getMission();

            MissionProgress missionProgress = new MissionProgress(
                missionEntity.getTitle(),
                missionEntity.getMissionId(),
                isCompleted);
            
            missionProgresses.add(missionProgress);
        }

        return new ProgressResponse("success", missionProgresses);
    }

    public ClearResponse clearMission(Long userId, int missionId){
        MyMission myMission = myMissionRepository.findById(missionId).orElse(null);
        String missinName = myMission.getMission().getTitle();

        myMission.setCompleted(true);
        myMissionRepository.save(myMission);

        MissionProgress missionProgress = new MissionProgress(
            missinName, 
            missionId, 
            true);

        return new ClearResponse("success",  missionProgress);
    }

    public ResetResponse resetMissions(Long userId){
        User user = userRepository.findById(userId).orElse(null);

        List<MyMission> myMissions = myMissionRepository.findByUserId(userId);
        myMissionRepository.deleteAll(myMissions);

        List<Mission> allMissions = missionRepository.findAll();

        Collections.shuffle(allMissions);
        List<Mission> randMissions = allMissions.stream().limit(3).collect(Collectors.toList());

        for(Mission misson : randMissions){
            MyMission newMission = new MyMission();
            newMission.setUser(user);
            newMission.setMission(misson);
            newMission.setCompleted(false);
            myMissionRepository.save(newMission);
    }

    return new ResetResponse("success");
    }

    public void initMissionTable(){
        List<Mission> missions = missionRepository.findAll();
        
        if(missions.isEmpty()){
            Mission mission1 = new Mission();
            mission1.setTitle("Mission 1");
            mission1.setMissionId(0);

            Mission mission2 = new Mission();
            mission2.setTitle("Mission 2");
            mission2.setMissionId(1);
            
            Mission mission3 = new Mission();
            mission3.setTitle("Mission 3");
            mission3.setMissionId(2);
        }
    }
}