package com.genesis.infrastructure.web.advice;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final List<String> messages;

    public ValidationErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        List<String> messages) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messages = messages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getMessages() {
        return messages;
    }
}
