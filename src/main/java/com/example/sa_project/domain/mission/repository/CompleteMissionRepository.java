package com.example.sa_project.domain.mission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sa_project.domain.mission.CompleteMission;

@Repository
public interface CompleteMissionRepository extends JpaRepository<CompleteMission, Integer>{
    List<CompleteMission> findByUserId(String userId);
}
