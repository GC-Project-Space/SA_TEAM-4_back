package com.example.sa_project.api.service.ranking;

import com.example.sa_project.api.service.ranking.response.MajorProgressDTO;
import com.example.sa_project.api.service.ranking.response.UserProgressDTO;
import com.example.sa_project.domain.ranking.UserProgress;
import com.example.sa_project.domain.ranking.UserProgressRepository;
import com.example.sa_project.domain.user.Major;
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

    public List<MajorProgressDTO> getMajorRankings() {
        List<Object[]> majorInfoSorted = userProgressRepository.findMajorInfoSorted();
        List<MajorProgressDTO> array = new ArrayList<>();
        majorInfoSorted.forEach(userProgress -> array.add(entityToDto(userProgress)));
        return array;
    }

    private UserProgressDTO entityToDto(UserProgress userProgress) {
        return new UserProgressDTO(userProgress.getUser().getId(), userProgress.getExperiencePoint());
    }

    private MajorProgressDTO entityToDto(Object[] userProgress) {
        // Object[] -> MajorProgressDTO로 변환
        Major major = (Major) userProgress[0];  // Major 객체로 캐스팅
        String majorName = major.getMajorName(); // majorName을 문자열로 추출
        Long totalExperiencePoint = (Long) userProgress[1]; // 총 경험치
        return new MajorProgressDTO(majorName, totalExperiencePoint);
    }


}
