package com.example.sa_project.api.service.ranking;

import com.example.sa_project.api.service.ranking.response.MajorProgressDTO;
import com.example.sa_project.api.service.ranking.response.SetProgressDTO;
import com.example.sa_project.api.service.ranking.response.UserProgressDTO;
import com.example.sa_project.domain.ranking.UserProgress;
import com.example.sa_project.domain.ranking.UserProgressRepository;
import com.example.sa_project.domain.user.Major;
import com.example.sa_project.domain.user.User;
import com.example.sa_project.domain.user.UserRepository;
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
    private final UserRepository userRepository;

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
        return new UserProgressDTO(userProgress.getUser().getId(),userProgress.getUser().getUsername() ,userProgress.getExperiencePoint(), userProgress.getSymbol());
    }

    private MajorProgressDTO entityToDto(Object[] userProgress) {
        // Object[] -> MajorProgressDTO로 변환
        Major major = (Major) userProgress[0];  // Major 객체로 캐스팅
        String majorName = major.getMajorName(); // majorName을 문자열로 추출
        Long totalExperiencePoint = (Long) userProgress[1]; // 총 경험치
        return new MajorProgressDTO(majorName, totalExperiencePoint);
    }

    public void saveUserProgress(SetProgressDTO setProgressDTO) {
        // 유저 정보 가져오기
        User user = userRepository.findById(setProgressDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + setProgressDTO.getUserId()));

        // 해당 유저의 UserProgress가 이미 있는지 확인
        UserProgress userProgress = userProgressRepository.findByUser(user).orElse(null);

        // 존재하면 experience 값만 갱신, 없으면 새로 생성
        if (userProgress != null) {
            userProgress.setExperiencePoint(setProgressDTO.getExperience_points());
        } else {
            userProgress = new UserProgress();
            userProgress.setUser(user);
            userProgress.setExperiencePoint(setProgressDTO.getExperience_points());
        }

        int exp = userProgress.getExperiencePoint();

        if(exp >= 0 && exp < 1000){
            userProgress.setSymbol("무당이");
        }else if(exp >= 1000 && exp < 2000){
            userProgress.setSymbol("무당짱");
        }else if(exp >= 2000 && exp < 3000){
            userProgress.setSymbol("무당왕");
        }else{
            userProgress.setSymbol("무당갓");
        }

        // 저장 (기존 레코드가 있다면 업데이트, 없다면 새로 생성됨)
        userProgressRepository.save(userProgress);
    }



}
