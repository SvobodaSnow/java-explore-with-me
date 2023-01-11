package ru.practicum.explorewithme.events.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.dto.ShortResponseEventDto;
import ru.practicum.explorewithme.events.service.PublicEventService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/events")
public class PublicEventController {
    @Autowired
    private PublicEventService publicEventService;

    @GetMapping
    public List<ShortResponseEventDto> getEvents(
            @RequestParam String text,
            @RequestParam List<Long> categories,
            @RequestParam boolean paid,
            @RequestParam String rangeStart,
            @RequestParam String rangeEnd,
            @RequestParam boolean onlyAvailable,
            @RequestParam String sort,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        log.info("Получен публичный запрос на формирования списка событий. IP: " + request.getRemoteAddr() +
                ". Path: " + request.getRequestURI());
        return publicEventService.getEvents(
                text,
                categories,
                paid,
                rangeStart,
                rangeEnd,
                onlyAvailable,
                sort,
                from,
                size,
                request
        );
    }

    @GetMapping("/{id}")
    public ResponseEventDto getEventById(@PathVariable Long id, HttpServletRequest request) {
        log.info("Получен публичный запрос на формирование события с ID " + id);
        return publicEventService.getEventById(id, request);
    }
}
