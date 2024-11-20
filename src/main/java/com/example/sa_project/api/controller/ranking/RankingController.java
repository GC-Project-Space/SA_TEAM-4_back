package com.example.sa_project.api.controller.ranking;

import com.example.sa_project.api.service.ranking.RankingService;
import com.example.sa_project.api.service.ranking.response.UserProgressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/major")
    public List<UserProgressDTO> getMajorRanking() {
        return rankingService.getMajorRankings();
    }
    @GetMapping("/all")
    public List<UserProgressDTO> getAllRankings() {
        return rankingService.getAllRankings();
    }
}