package com.example.sa_project.domain.user;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Major {

    소프트웨어학과("소프트웨어학과"),
    AI인공지능학과("AI인공지능학과");

    private final String majorName;

    @JsonValue
    public String getMajorName() {
        return majorName;
    }
}