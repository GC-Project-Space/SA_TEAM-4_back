package com.example.sa_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "complete_mission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompleteMission {
    
    @Column(name="user_id")
    private String userId;

    @Id
    @Column(name="mission_id")
    private int missionId;
}
