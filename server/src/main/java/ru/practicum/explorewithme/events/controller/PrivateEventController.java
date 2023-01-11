package ru.practicum.explorewithme.events.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.events.dto.NewEventDto;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.dto.ShortResponseEventDto;
import ru.practicum.explorewithme.events.dto.UpdateEventDto;
import ru.practicum.explorewithme.events.service.PrivateEventService;
import ru.practicum.explorewithme.requests.dto.RequestDto;
import ru.practicum.explorewithme.requests.service.RequestService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/users/{userId}/events")
public class PrivateEventController {
    @Autowired
    private PrivateEventService privateEventService;
    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEventDto createNewEvent(@RequestBody NewEventDto newEventDto, @PathVariable Long userId) {
        log.info("Получен запрос на добавление нового события от пользователя с ID: " + userId);
        return privateEventService.createNewEvent(newEventDto, userId);
    }

    @GetMapping
    public List<ShortResponseEventDto> getEventByInitiatorId(@PathVariable Long userId) {
        log.info("Получен запрос на составление списка событий для пользователя с ID: " + userId +
                " в которых он является инициатором");
        return privateEventService.getEventByInitiatorId(userId);
    }

    @PatchMapping
    public ResponseEventDto updateEvent(@RequestBody UpdateEventDto updateEventDto, @PathVariable Long userId) {
        log.info("Получен запрос на обновление события с ID: " + updateEventDto.getEventId() +
                ". Запрос от пользователя с ID: " + userId);
        return privateEventService.updateEvent(updateEventDto, userId);
    }

    @GetMapping("/{eventId}")
    public ResponseEventDto getEventById(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Получен запрос на формирование события с ID: " + eventId +
                ". Запрос получен от пользователя с ID: " + userId);
        return privateEventService.getEventById(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public ResponseEventDto cancelEventById(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Получен запрос на отмену события с ID: " + eventId +
                ". ID пользователя, отменяющего событие: " + userId);
        return privateEventService.cancelEventById(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public List<RequestDto> getAllRequestsForEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Получен запрос на формирования списка запросов на участия в событии. ID пользователя " + userId +
                ". ID события " + eventId);
        return requestService.getAllRequestsForEvent(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public RequestDto confirmRequest(@PathVariable Long userId, @PathVariable Long eventId, @PathVariable Long reqId) {
        log.info("Получен запрос на подтверждение заявки на участие в событии. ID заявки " + reqId +
                ". ID пользователя " + userId + ". ID события " + eventId);
        return requestService.confirmRequest(userId, eventId, reqId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public RequestDto rejectRequest(@PathVariable Long userId, @PathVariable Long eventId, @PathVariable Long reqId) {
        log.info("Получен запрос на отклонение заявки на участие в событии. ID заявки " + reqId +
                ". ID пользователя " + userId + ". ID события " + eventId);
        return requestService.rejectRequest(userId, eventId, reqId);
    }
}
