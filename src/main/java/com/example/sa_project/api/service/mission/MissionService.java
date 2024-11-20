package com.example.sa_project.api.service.mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sa_project.api.service.mission.response.ClearResponse;
import com.example.sa_project.api.service.mission.response.MissionProgress;
import com.example.sa_project.api.service.mission.response.ProgressResponse;
import com.example.sa_project.api.service.mission.response.ResetResponse;
import com.example.sa_project.domain.mission.MyMission;
import com.example.sa_project.domain.mission.Mission;
import com.example.sa_project.domain.mission.MyMissionRepository;
import com.example.sa_project.domain.mission.MissionRepository;
import com.example.sa_project.domain.user.Member;
import com.example.sa_project.domain.user.MemberRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MissionService {

    private final MyMissionRepository myMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public MissionService(MyMissionRepository myMissionRepository, MissionRepository missionRepository, MemberRepository memberRepository) {
        this.myMissionRepository = myMissionRepository;
        this.missionRepository = missionRepository;
        this.memberRepository = memberRepository;
    }

    public ProgressResponse getMissionProgress(Long userId) {
        List<MyMission> myMissions = myMissionRepository.findByUserId(userId);

        List<MissionProgress> missionProgresses = new ArrayList<>();

        for(MyMission mission : myMissions){
            boolean isCompleted = mission.isCompleted();
            Mission missionEntity = mission.getMissionId();

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
        String missinName = myMission.getMissionId().getTitle();

        myMission.setCompleted(true);
        myMissionRepository.save(myMission);

        MissionProgress missionProgress = new MissionProgress(
            missinName, 
            missionId, 
            true);

        return new ClearResponse("success",  missionProgress);
    }

    public ResetResponse resetMissions(Long userId){
        Member member = memberRepository.findById(userId).orElse(null);

        List<MyMission> myMissions = myMissionRepository.findByUserId(userId);
        myMissionRepository.deleteAll(myMissions);

        List<Mission> allMissions = missionRepository.findAll();

        Collections.shuffle(allMissions);
        List<Mission> randMissions = allMissions.stream().limit(3).collect(Collectors.toList());

        for(Mission misson : randMissions){
            MyMission newMission = new MyMission();
            newMission.setMemberId(member);
            newMission.setMissionId(misson);
            newMission.setCompleted(false);
            myMissionRepository.save(newMission);
    }

    return new ResetResponse("success");
    }
}