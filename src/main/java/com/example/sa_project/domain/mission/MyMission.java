package com.example.sa_project.domain.mission;

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
public class MyMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "missionId")
    private Mission missionId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    private boolean isCompleted;
}
