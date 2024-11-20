package com.example.sa_project.domain.ranking;

import com.example.sa_project.domain.user.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @OneToOne(mappedBy = "userProgress")
    @JoinColumn(name = "user_id")
    private Member member;

    private int experiencePoint;
}
