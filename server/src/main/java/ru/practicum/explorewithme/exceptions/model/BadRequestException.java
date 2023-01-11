package ru.practicum.explorewithme.exceptions.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class BadRequestException extends RuntimeException {
    private final String message;
    private final String reason;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public BadRequestException(String massage, String reason, LocalDateTime timestamp) {
        this.message = massage;
        this.reason = reason;
        this.status = HttpStatus.BAD_REQUEST;
        this.timestamp = timestamp;
    }
}
