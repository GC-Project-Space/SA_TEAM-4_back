package com.example.sa_project.api.service.ranking.response;

public class UserProgressDTO {
    public  String name; //user name && major name
    public Integer experience_points;

    public UserProgressDTO(String Name, Long experiencePoints) {
        this.name = Name;
        this.experience_points = Math.toIntExact(experiencePoints);
    }
}