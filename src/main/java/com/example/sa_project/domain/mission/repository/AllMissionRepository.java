package com.example.sa_project.domain.mission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sa_project.domain.mission.MyMission;

@Repository
public interface AllMissionRepository extends JpaRepository<MyMission, Integer>{
    List<MyMission> findByUserId(Long userId);
}
