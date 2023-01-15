package ru.practicum.explorewithme.events.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.dto.ShortResponseEventDto;
import ru.practicum.explorewithme.events.service.PublicEventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.text.MessageFormat;
import java.util.List;

import static ru.practicum.explorewithme.client.model.GlobalVariables.DEFAULT_TIME_END;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/events")
public class PublicEventController {
    @Autowired
    private PublicEventService publicEventService;

    @GetMapping
    public List<ShortResponseEventDto> getEvents(
            @RequestParam(defaultValue = "") String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(defaultValue = DEFAULT_TIME_END) String rangeEnd,
            @RequestParam(required = false) boolean onlyAvailable,
            @RequestParam(defaultValue = "EVENT_DATE") String sort,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        log.info(
                MessageFormat.format(
                        "Получен публичный запрос на формирования списка событий. IP: {0}. Path: {1}",
                        request.getRemoteAddr(),
                        request.getRequestURI()
                )
        );
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
    public ResponseEventDto getEventById(@Positive @PathVariable Long id, HttpServletRequest request) {
        log.info(MessageFormat.format("Получен публичный запрос на формирование события с ID {0}", id));
        return publicEventService.getEventById(id, request);
    }
}
