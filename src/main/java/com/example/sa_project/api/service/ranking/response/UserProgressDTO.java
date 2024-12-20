package com.example.sa_project.api.service.ranking.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProgressDTO {
    public Long id;
    public String name;
    public Integer experience_points;
    public String symbol;
}
