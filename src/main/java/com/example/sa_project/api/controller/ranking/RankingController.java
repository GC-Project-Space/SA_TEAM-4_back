package com.example.sa_project.api.controller.ranking;

import com.example.sa_project.api.service.ranking.RankingService;
import com.example.sa_project.api.service.ranking.response.MajorProgressDTO;
import com.example.sa_project.api.service.ranking.response.SetProgressDTO;
import com.example.sa_project.api.service.ranking.response.UserProgressDTO;
import com.example.sa_project.config.CustomUserDetails;
import com.example.sa_project.domain.ranking.UserProgress;
import com.example.sa_project.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/major")
    public List<MajorProgressDTO> getMajorRanking() {
        return rankingService.getMajorRankings();
    }
    @GetMapping("/all")
    public List<UserProgressDTO> getAllRankings() {
        return rankingService.getAllRankings();
    }
    @PostMapping("/save")
    public String saveUserProgress(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestBody SetProgressDTO setProgressDTO) {
        Long userId = customUserDetails.getUserId();
        setProgressDTO.setUserId(userId);
        rankingService.saveUserProgress(setProgressDTO);
        return "success";
    }
}