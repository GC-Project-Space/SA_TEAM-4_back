package com.example.sa_project.Ranking;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Major {

    CS("Computer Science"),
    EE("Electrical Engineering"),
    ME("Mechanical Engineering"),
    BIO("Biology"),
    CHEM("Chemistry");

    private final String majorName;

    @JsonValue
    public String getMajorName() {
        return majorName;
    }
}
