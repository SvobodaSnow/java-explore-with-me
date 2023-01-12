package ru.practicum.explorewithme.requests.service;


import org.springframework.web.bind.annotation.PathVariable;
import ru.practicum.explorewithme.requests.dto.RequestDto;

import java.util.List;

public interface RequestService {
    RequestDto createNewRequest(Long requesterId, Long eventId);

    List<RequestDto> getAllRequestsForUser(Long requesterId);

    RequestDto cancelRequest(Long userId, Long reqId);

    List<RequestDto> getAllRequestsForEvent(Long userId, Long eventId);

    RequestDto confirmRequest(@PathVariable Long userId, @PathVariable Long eventId, @PathVariable Long reqId);

    RequestDto rejectRequest(@PathVariable Long userId, @PathVariable Long eventId, @PathVariable Long reqId);
}
