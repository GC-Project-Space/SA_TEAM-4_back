package com.example.sa_project.domain.ranking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.sa_project.domain.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long>{

    @Query("SELECT u FROM UserProgress u ORDER BY u.experiencePoint DESC")
    List<UserProgress> findAllWithUserInfoSorted();

    @Query("SELECT u.major, SUM(up.experiencePoint) FROM UserProgress up JOIN up.user u GROUP BY u.major ORDER BY SUM(up.experiencePoint) DESC")
    List<Object[]> findMajorInfoSorted();

    Optional<UserProgress> findByUser(User user);
}
