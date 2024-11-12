package com.example.sa_project.domain.mission.repository;

import com.example.sa_project.api.service.ranking.response.UserProgressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sa_project.domain.mission.UserProgress;

import java.util.List;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long>{

    @Query("SELECT u FROM UserProgress u ORDER BY u.experiencePoint DESC")
    List<UserProgress> findAllWithUserInfoSorted();

    @Query("SELECT u, SUM(u.experiencePoint) FROM UserProgress u GROUP BY u.major ORDER BY SUM(u.experiencePoint) DESC")
    List<UserProgress> findMajorInfoSorted();

}
