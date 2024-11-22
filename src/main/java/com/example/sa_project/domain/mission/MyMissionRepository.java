package com.example.sa_project.domain.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyMissionRepository extends JpaRepository<MyMission, Integer>{
    List<MyMission> findByUserId(Long userId);


    @Query("SELECT m FROM MyMission m WHERE m.user.id = :userId AND m.mission.id = :missionId")
    Optional<MyMission> findByUserIdAndMissionId(@Param("userId") Long userId, @Param("missionId") int missionId);
}
