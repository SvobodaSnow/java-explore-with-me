package ru.practicum.explorewithme.events.service;

import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.dto.ShortResponseEventDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PublicEventService {
    List<ShortResponseEventDto> getEvents(
            String text,
            List<Long> categories,
            boolean paid,
            String rangeStart,
            String rangeEnd,
            boolean onlyAvailable,
            String sort,
            int from,
            int size,
            HttpServletRequest request
    );

    ResponseEventDto getEventById(Long id, HttpServletRequest request);
}
