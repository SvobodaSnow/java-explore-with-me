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

import javax.validation.constraints.Positive;
import java.text.MessageFormat;
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
    public ResponseEventDto createNewEvent(@RequestBody NewEventDto newEventDto, @Positive @PathVariable Long userId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на добавление нового события от пользователя с ID: {0}",
                        userId
                )
        );
        return privateEventService.createNewEvent(newEventDto, userId);
    }

    @GetMapping
    public List<ShortResponseEventDto> getEventByInitiatorId(@Positive @PathVariable Long userId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на составление списка событий для пользователя с ID: {0} " +
                                "в которых он является инициатором",
                        userId
                )
        );
        return privateEventService.getEventByInitiatorId(userId);
    }

    @PatchMapping
    public ResponseEventDto updateEvent(
            @RequestBody UpdateEventDto updateEventDto,
            @Positive @PathVariable Long userId
    ) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на обновление события с ID: {0}. Запрос от пользователя с ID: {1}",
                        updateEventDto.getEventId(),
                        userId
                )
        );
        return privateEventService.updateEvent(updateEventDto, userId);
    }

    @GetMapping("/{eventId}")
    public ResponseEventDto getEventById(@Positive @PathVariable Long userId, @Positive @PathVariable Long eventId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на формирование события с ID: {0}. " +
                                "Запрос получен от пользователя с ID: {1}",
                        eventId,
                        userId
                )
        );
        return privateEventService.getEventById(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public ResponseEventDto cancelEventById(@Positive @PathVariable Long userId, @Positive @PathVariable Long eventId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на отмену события с ID: {0}. ID пользователя, отменяющего событие: {1}",
                        eventId,
                        userId
                )
        );
        return privateEventService.cancelEventById(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public List<RequestDto> getAllRequestsForEvent(
            @Positive @PathVariable Long userId,
            @Positive @PathVariable Long eventId
    ) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на формирования списка запросов на участия в событии. " +
                                "ID пользователя {0}. ID события {1}",
                        userId,
                        eventId
                )
        );
        return requestService.getAllRequestsForEvent(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public RequestDto confirmRequest(
            @Positive @PathVariable Long userId,
            @Positive @PathVariable Long eventId,
            @Positive @PathVariable Long reqId
    ) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на подтверждение заявки на участие в событии. ID заявки {0}. " +
                                "ID пользователя {1}. ID события {2}",
                        reqId,
                        userId,
                        eventId
                )
        );
        return requestService.confirmRequest(userId, eventId, reqId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public RequestDto rejectRequest(
            @Positive @PathVariable Long userId,
            @Positive @PathVariable Long eventId,
            @Positive @PathVariable Long reqId
    ) {

        log.info(
                MessageFormat.format(
                        "Получен запрос на отклонение заявки на участие в событии. ID заявки {0}. " +
                                "ID пользователя {1}. ID события {2}",
                        reqId,
                        userId,
                        eventId
                )
        );
        return requestService.rejectRequest(userId, eventId, reqId);
    }
}
