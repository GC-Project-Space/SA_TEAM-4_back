package com.example.sa_project.Ranking;

import com.example.sa_project.Entity.UserProgressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/Ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/major")
    public List<UserProgressDTO> getMajorRanking() {
        return rankingService.getMajorRankings();
    }
    @GetMapping("/all")
    public List<UserProgressDTO> getAllRankings() {
        return rankingService.getAllRankings();
    }
}