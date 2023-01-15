package ru.practicum.explorewithme.requests;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.requests.dto.RequestDto;
import ru.practicum.explorewithme.requests.service.RequestService;

import javax.validation.constraints.Positive;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/users/{userId}/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping
    public RequestDto createNewRequest(@Positive @PathVariable Long userId, @Positive @RequestParam Long eventId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на добавление запроса на присоединения к событию. " +
                                "ID пользователя: {0}. ID события: {1}",
                        userId,
                        eventId
                )
        );
        return requestService.createNewRequest(userId, eventId);
    }

    @GetMapping
    public List<RequestDto> getAllRequestsForUser(@Positive @PathVariable Long userId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на формирование списка запросов на участие в событиях " +
                                "для пользователя с ID: {0}",
                        userId
                )
        );
        return requestService.getAllRequestsForUser(userId);
    }

    @PatchMapping("/{reqId}/cancel")
    public RequestDto cancelRequest(@Positive @PathVariable Long userId, @Positive @PathVariable Long reqId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на отмену запроса на участие в событии. ID пользователя {0}. " +
                                "ID запроса на учатие: {1}",
                        userId,
                        reqId
                )
        );
        return requestService.cancelRequest(userId, reqId);
    }
}
