package ru.practicum.explorewithme.events.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.events.dto.NewEventDto;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.model.State;
import ru.practicum.explorewithme.events.service.AdminEventService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.text.MessageFormat;
import java.util.List;

import static ru.practicum.explorewithme.client.model.GlobalVariables.*;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/events")
public class AdminEventController {
    @Autowired
    private AdminEventService adminEventService;

    @GetMapping
    public List<ResponseEventDto> getEvents(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<State> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(defaultValue = DEFAULT_TIME_START) String rangeStart,
            @RequestParam(defaultValue = DEFAULT_TIME_END) String rangeEnd,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {

        log.info(
                MessageFormat.format(
                        "Получен запрос на формирование списка событий для пользователей с ID: {0}. " +
                                "Состояния для поиска: {1}. Список категорий: {2}. Диапазон с {3} до {4}",
                        users,
                        states,
                        categories,
                        rangeStart,
                        rangeEnd
                )
        );
        return adminEventService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("/{eventId}")
    public ResponseEventDto updateEvent(@RequestBody NewEventDto newEventDto, @Positive @PathVariable Long eventId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на обновление события с ID {0} от администратора",
                        eventId
                )
        );
        return adminEventService.updateEvent(newEventDto, eventId);
    }

    @PatchMapping("/{eventId}/publish")
    public ResponseEventDto publishEvent(@Positive @PathVariable Long eventId) {
        log.info(MessageFormat.format("Получен запрос на публикацию события с ID {0}", eventId));
        return adminEventService.publishEvent(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    public ResponseEventDto rejectEvent(@Positive @PathVariable Long eventId) {
        log.info(MessageFormat.format("Получен запрос на отклонение события с ID {0}", eventId));
        return adminEventService.rejectEvent(eventId);
    }
}
