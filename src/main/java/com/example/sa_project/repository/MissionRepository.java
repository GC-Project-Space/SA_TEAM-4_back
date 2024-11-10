package com.example.sa_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sa_project.entity.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {

}
