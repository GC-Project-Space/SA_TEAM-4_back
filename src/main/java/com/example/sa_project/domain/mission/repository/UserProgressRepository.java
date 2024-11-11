package com.example.sa_project.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sa_project.domain.mission.UserProgress;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, String>{
    
}
