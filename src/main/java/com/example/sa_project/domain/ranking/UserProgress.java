package com.example.sa_project.domain.ranking;

import com.example.sa_project.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략 추가
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true) // user_id는 외래 키
    private User user;

    private int experiencePoint;

    @Transient
    private String symbol;

    public String getSymbol() {
        if (this.experiencePoint >= 0 && this.experiencePoint < 100) {
            return "무당이";
        } else if (this.experiencePoint >= 100 && this.experiencePoint < 200) {
            return "무당짱";
        } else if (this.experiencePoint >= 200 && this.experiencePoint < 300) {
            return "무당왕";
        } else if (this.experiencePoint >= 300) {
            return "무당갓";
        }
        return null;
    }
}
