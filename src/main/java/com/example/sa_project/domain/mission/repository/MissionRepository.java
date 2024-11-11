package com.example.sa_project.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sa_project.domain.mission.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {

}
