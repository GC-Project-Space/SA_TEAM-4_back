package com.example.sa_project.domain.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyMissionRepository extends JpaRepository<MyMission, Integer>{
    List<MyMission> findByUserId(Long userId);
}
