package com.example.sa_project.api.service.ranking.response;

import com.example.sa_project.domain.user.User;
import lombok.Data;

@Data
public class SetProgressDTO {
    public Long userId;
    public Integer experience_points;
}