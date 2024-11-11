package com.example.sa_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sa_project.entity.AllMission;

@Repository
public interface AllMissionRepository extends JpaRepository<AllMission, Integer>{
    List<AllMission> findByUserId(String userId);
}
