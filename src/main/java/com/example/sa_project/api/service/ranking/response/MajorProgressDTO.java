package com.example.sa_project.api.service.ranking.response;

import com.example.sa_project.domain.user.Major;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajorProgressDTO {
//    private int rank;
    private String major;
    public Long experience_points;
}
