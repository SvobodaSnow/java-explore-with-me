package ru.practicum.explorewithme.events.service;


import ru.practicum.explorewithme.events.dto.NewEventDto;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.dto.ShortResponseEventDto;
import ru.practicum.explorewithme.events.dto.UpdateEventDto;

import java.util.List;

public interface PrivateEventService {
    ResponseEventDto createNewEvent(NewEventDto newEventDto, Long userId);

    List<ShortResponseEventDto> getEventByInitiatorId(Long userId);

    ResponseEventDto updateEvent(UpdateEventDto updateEventDto, Long userId);

    ResponseEventDto getEventById(Long userId, Long eventId);

    ResponseEventDto cancelEventById(Long userId, Long eventId);
}
