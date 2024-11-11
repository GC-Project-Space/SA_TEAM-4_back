package com.example.sa_project.domain.mission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mission {
    @Id
    @Column(name="mission_id")
    private int missionId;

    @Column(name="mission_title")
    private String title;

}
