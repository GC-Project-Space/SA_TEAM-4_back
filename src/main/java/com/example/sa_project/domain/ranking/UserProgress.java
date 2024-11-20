package com.example.sa_project.domain.ranking;

import com.example.sa_project.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
}
