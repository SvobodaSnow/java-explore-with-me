package ru.practicum.explorewithme.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explorewithme.exceptions.model.BadRequestException;
import ru.practicum.explorewithme.exceptions.model.ConflictException;
import ru.practicum.explorewithme.exceptions.model.NotFoundException;
import ru.practicum.explorewithme.exceptions.model.PaymentRequiredException;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlerBadRequestException(final BadRequestException e) {
        log.error(e.getMessage());
        return Map.of(
                "message", e.getMessage(),
                "reason", e.getReason(),
                "status", "BAD_REQUEST",
                "timestamp", e.getTimestamp().toString()
        );
    }

    @ExceptionHandler({ConflictException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handlerConflictException(final ConflictException e) {
        log.error(e.getMessage());
        return Map.of(
                "message", e.getMessage(),
                "reason", e.getReason(),
                "status", "CONFLICT",
                "timestamp", e.getTimestamp().toString()
        );
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlerNotFoundException(final NotFoundException e) {
        log.error(e.getMessage());
        return Map.of(
                "message", e.getMessage(),
                "reason", e.getReason(),
                "status", "NOT_FOUND",
                "timestamp", e.getTimestamp().toString()
        );
    }

    @ExceptionHandler({PaymentRequiredException.class})
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public Map<String, String> handlerPaymentRequiredException(final PaymentRequiredException e) {
        log.error(e.getMessage());
        return Map.of(
                "message", e.getMessage(),
                "reason", e.getReason(),
                "status", "PAYMENT_REQUIRED",
                "timestamp", e.getTimestamp().toString()
        );
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlerHttpRequestMethodNotSupportedException(
            final HttpRequestMethodNotSupportedException e
    ) {
        log.error(e.getMessage());
        return Map.of(
                "message", e.getMessage(),
                "reason", "",
                "status", "BAD_REQUEST",
                "timestamp", LocalDateTime.now().toString()
        );
    }
}
