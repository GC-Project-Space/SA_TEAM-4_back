package com.example.sa_project.Ranking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RankingRepository extends JpaRepository<User, Long> {

    @Query("SELECT new com.example.sa_project.Ranking.UserProgressDTO(u.username, up.experiencePoint) " +
            "FROM UserProgress up JOIN up.user u ORDER BY (up.experiencePoint) DESC")
    List<UserProgressDTO> findAllWithUserInfoSorted();

    @Query("SELECT new com.example.sa_project.Ranking.MajorProgressDTO(u.major, SUM(up.experiencePoint)) " +
            "FROM UserProgress up JOIN up.user u " +
            "GROUP BY u.major " +
            "ORDER BY SUM(up.experiencePoint) DESC")
    List<MajorProgressDTO> findMajorInfoSorted();
}


