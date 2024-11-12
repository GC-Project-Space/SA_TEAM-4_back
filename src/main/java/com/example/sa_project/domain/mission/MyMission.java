package com.example.sa_project.domain.mission;

import com.example.sa_project.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyMission {

    @Id
    @ManyToOne
    @JoinColumn(name = "missionId")
    private Mission missionId;

    @OneToMany(mappedBy = "user")
    private List<User> userId = new ArrayList<>();

    private boolean isCompleted;
}
