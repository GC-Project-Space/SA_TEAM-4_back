package com.example.sa_project.api.service.ranking;

import com.example.sa_project.api.service.ranking.response.UserProgressDTO;
import com.example.sa_project.domain.ranking.RankingRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RankingService {

    private final com.example.sa_project.domain.ranking.RankingRepository RankingRepository;

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
