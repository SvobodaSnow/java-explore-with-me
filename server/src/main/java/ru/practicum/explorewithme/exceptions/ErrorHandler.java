package ru.practicum.explorewithme.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explorewithme.exceptions.model.BadRequestException;
import ru.practicum.explorewithme.exceptions.model.ConflictException;
import ru.practicum.explorewithme.exceptions.model.NotFoundException;
import ru.practicum.explorewithme.exceptions.model.PaymentRequiredException;

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
                "status", e.getStatus().toString(),
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
                "status", e.getStatus().toString(),
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
                "status", e.getStatus().toString(),
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
                "status", e.getStatus().toString(),
                "timestamp", e.getTimestamp().toString()
        );
    }
}
