package com.example.sa_project.api.service.ranking;

import com.example.sa_project.api.service.ranking.response.UserProgressDTO;
import com.example.sa_project.domain.ranking.UserProgress;
import com.example.sa_project.domain.ranking.UserProgressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RankingService {

    private final UserProgressRepository userProgressRepository;

    public List<UserProgressDTO> getAllRankings() {
        List<UserProgress> allWithUserInfoSorted = userProgressRepository.findAllWithUserInfoSorted();
        List<UserProgressDTO> array = new ArrayList<>();
        allWithUserInfoSorted.forEach(userProgress -> array.add(entityToDto(userProgress)));
        return array;
    }

    public List<UserProgressDTO> getMajorRankings() {
        List<UserProgress> majorInfoSorted = userProgressRepository.findMajorInfoSorted();
        List<UserProgressDTO> array = new ArrayList<>();
        majorInfoSorted.forEach(userProgress -> array.add(entityToDto(userProgress)));
        return array;
    }

    private UserProgressDTO entityToDto(UserProgress userProgress) {
        return new UserProgressDTO(userProgress.getUser().getId(), userProgress.getExperiencePoint());
    }
}
