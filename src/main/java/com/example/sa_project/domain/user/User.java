package com.example.sa_project.domain.user;

import com.example.sa_project.domain.mission.UserProgress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role ;

    @Enumerated(EnumType.STRING)
    private Major major;

    @OneToOne(cascade = CascadeType.ALL)
    private UserProgress userProgress;


    // Getters and Setters
}