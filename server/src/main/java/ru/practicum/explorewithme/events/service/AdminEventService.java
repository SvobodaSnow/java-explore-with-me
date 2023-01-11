package ru.practicum.explorewithme.events.service;

import ru.practicum.explorewithme.events.dto.NewEventDto;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.model.State;

import java.util.List;

public interface AdminEventService {
    List<ResponseEventDto> getEvents(
            List<Long> users,
            List<State> states,
            List<Long> categories,
            String rangeStart,
            String rangeEnd,
            int from,
            int size
    );

    ResponseEventDto updateEvent(NewEventDto newEventDto, Long eventId);

    ResponseEventDto publishEvent(Long eventId);

    ResponseEventDto rejectEvent(Long eventId);
}
