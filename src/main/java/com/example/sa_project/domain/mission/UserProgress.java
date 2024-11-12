package com.example.sa_project.domain.mission;

import com.example.sa_project.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    @OneToOne(mappedBy = "user")
    private User userId;

    private int experiencePoint;
}
