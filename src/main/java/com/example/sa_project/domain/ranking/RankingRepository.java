package com.example.sa_project.domain.ranking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sa_project.api.service.ranking.response.UserProgressDTO;

@Repository
public interface RankingRepository extends JpaRepository<UserProgressEntity, Long> {

    @Query("SELECT new com.example.sa_project.api.service.ranking.response.UserProgressDTO(u.userName, u.experiencePoints) " +
            "FROM UserProgressEntity u ORDER BY u.experiencePoints DESC")
    List<UserProgressDTO> findAllWithUserInfoSorted();

    @Query("SELECT new com.example.sa_project.api.service.ranking.response.UserProgressDTO(u.major, SUM(u.experiencePoints)) " +
            "FROM UserProgressEntity u GROUP BY u.major ORDER BY SUM(u.experiencePoints) DESC")
    List<UserProgressDTO> findMajorInfoSorted();
}
