package com.example.sa_project.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="userprogressentity")
public class UserProgressEntity {

    @Id
    public String user_id;

    @Column
    public Long experience_points;

    @Column
    public String user_name;

    @Column
    public String major;
}