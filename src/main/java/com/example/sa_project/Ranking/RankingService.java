package com.example.sa_project.Ranking;

import com.example.sa_project.Entity.UserProgressEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RankingService {

    private final RankingRepository RankingRepository;

    @Autowired
    public RankingService(RankingRepository RankingRepository) {
        this.RankingRepository = RankingRepository;
    }

    public List<UserProgressDTO> getAllRankings() {
        return RankingRepository.findAllWithUserInfoSorted();
    }

    public List<UserProgressDTO> getMajorRankings() {
        return RankingRepository.findMajorInfoSorted();
    }

}
