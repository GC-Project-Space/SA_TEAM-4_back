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

    public ClearResponse clearMission(Long userId, int missionId) {
        // userId와 missionId에 해당하는 MyMission을 찾기
        MyMission myMission = myMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new RuntimeException("MyMission not found for userId: " + userId + " and missionId: " + missionId));

        // Mission이 null인 경우 처리
        if (myMission.getMission() == null) {
            throw new RuntimeException("Mission not found for MyMission with id: " + missionId);
        }

        // 미션 이름 가져오기
        String missionName = myMission.getMission().getTitle();

        // 미션 완료 처리
        myMission.setCompleted(true);
        myMissionRepository.save(myMission);

        // MissionProgress 생성
        MissionProgress missionProgress = new MissionProgress(
                missionName,
                missionId,
                true
        );

        return new ClearResponse("success", missionProgress);
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
}