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
@Table(name = "user_progress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProgress {
    @Id
    @Column(name="user_id")
    private String id;
    
    @Column(name="user_level")
    private int level;

    @Column(name="experience_point")
    private int experiencePoint;
}
