package ru.practicum.explorewithme.exceptions.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class PaymentRequiredException extends RuntimeException {
    private final String message;
    private final String reason;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public PaymentRequiredException(String massage, String reason, LocalDateTime timestamp) {
        this.message = massage;
        this.reason = reason;
        this.status = HttpStatus.PAYMENT_REQUIRED;
        this.timestamp = timestamp;
    }
}
