package com.example.sa_project.api.controller.response;

public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }
}
