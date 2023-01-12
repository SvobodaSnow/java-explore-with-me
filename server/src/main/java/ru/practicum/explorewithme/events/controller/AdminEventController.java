package ru.practicum.explorewithme.events.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.events.dto.NewEventDto;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.model.State;
import ru.practicum.explorewithme.events.service.AdminEventService;

import java.util.List;

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
            @RequestParam(defaultValue = "0001-01-01 00:00:00") String rangeStart,
            @RequestParam(defaultValue = "9999-12-31 00:00:00") String rangeEnd,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен запрос на формирование списка событий для пользователей с ID: " + users +
                ". Состояния для поиска: " + states + ". Список категорий: " + categories +
                ". Диапазон с " + rangeStart + " до " + rangeEnd);
        return adminEventService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("/{eventId}")
    public ResponseEventDto updateEvent(@RequestBody NewEventDto newEventDto, @PathVariable Long eventId) {
        log.info("Получен азпрос на обновление события с ID " + eventId + " от администратора");
        return adminEventService.updateEvent(newEventDto, eventId);
    }

    @PatchMapping("/{eventId}/publish")
    public ResponseEventDto publishEvent(@PathVariable Long eventId) {
        log.info("Получен запрос на публикацию события с ID " + eventId);
        return adminEventService.publishEvent(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    public ResponseEventDto rejectEvent(@PathVariable Long eventId) {
        log.info("Получен запрос на отклонение события с ID " + eventId);
        return adminEventService.rejectEvent(eventId);
    }
}
