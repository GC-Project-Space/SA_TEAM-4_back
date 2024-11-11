package com.example.sa_project.domain.ranking;

import jakarta.persistence.*;

@Entity
@Table(name="userprogressentity")
public class UserProgressEntity {

    @Id
    public String userId;

    @Column
    public Long experiencePoints;

    @Column
    public String userName;

    @Column
    public String major;
}