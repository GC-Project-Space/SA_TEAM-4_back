package com.example.sa_project.Ranking;

import com.example.sa_project.Entity.UserProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RankingRepository extends JpaRepository<UserProgressEntity, Long> {

    @Query("SELECT new com.example.sa_project.Ranking.UserProgressDTO(u.user_name, u.experience_points) " +
            "FROM UserProgressEntity u ORDER BY u.experience_points DESC")
    List<UserProgressDTO> findAllWithUserInfoSorted();

    @Query("SELECT new com.example.sa_project.Ranking.UserProgressDTO(u.major, SUM(u.experience_points)) " +
            "FROM UserProgressEntity u GROUP BY u.major ORDER BY SUM(u.experience_points) DESC")
    List<UserProgressDTO> findMajorInfoSorted();
}


