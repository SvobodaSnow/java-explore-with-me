package ru.practicum.explorewithme.requests;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.requests.dto.RequestDto;
import ru.practicum.explorewithme.requests.service.RequestService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/users/{userId}/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping
    public RequestDto createNewRequest(@PathVariable Long userId, @RequestParam Long eventId) {
        log.info("Получен запрос на добавление запроса на присоединения к событию. ID пользователя: " + userId +
                ". ID события: " + eventId);
        return requestService.createNewRequest(userId, eventId);
    }

    @GetMapping
    public List<RequestDto> getAllRequestsForUser(@PathVariable Long userId) {
        log.info("Получен запрос на формирование списка запросов на участие в событиях " +
                "для пользователя с ID: " + userId);
        return requestService.getAllRequestsForUser(userId);
    }

    @PatchMapping("/{reqId}/cancel")
    public RequestDto cancelRequest(@PathVariable Long userId, @PathVariable Long reqId) {
        log.info("Получен запрос на отмену запроса на участие в событии. ID пользователя " + userId +
                ". ID запроса на учатие: " + reqId);
        return requestService.cancelRequest(userId, reqId);
    }
}
