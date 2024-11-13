package com.example.sa_project.Ranking;

import com.example.sa_project.Ranking.User;
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
@Table(name = "userprogress")
public class UserProgress {

    @Id
    @OneToOne
    @MapsId // User의 ID를 UserProgress의 기본 키로 매핑
    @JoinColumn(name = "user_id") // 외래 키로 설정
    private User user;


    @Column(name = "experiencePoint")
    private int experiencePoint;
}
