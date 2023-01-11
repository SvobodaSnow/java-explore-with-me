package ru.practicum.explorewithme.events.dto;

import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.events.model.Event;
import ru.practicum.explorewithme.events.model.State;
import ru.practicum.explorewithme.users.model.User;

import java.time.LocalDateTime;

public class EventMapper {
    public static Event toNewEvent(NewEventDto newEventDto, User initiator, Categories categories) {
        return new Event(
                null,
                newEventDto.getAnnotation(),
                categories,
                0,
                LocalDateTime.now(),
                newEventDto.getDescription(),
                newEventDto.getEventDate(),
                initiator,
                newEventDto.getLocation(),
                newEventDto.isPaid(),
                newEventDto.getParticipantLimit(),
                true,
                null,
                newEventDto.isRequestModeration(),
                State.PENDING,
                newEventDto.getTitle(),
                0L
        );
    }

    public static NewEventDto toNewEventDto(Event event) {
        return new NewEventDto(
                event.getAnnotation(),
                event.getCategory().getId(),
                event.getDescription(),
                event.getEventDate(),
                event.getLocation(),
                event.isPaid(),
                event.getParticipantLimit(),
                event.isRequestModeration(),
                event.getTitle()
        );
    }

    public static ResponseEventDto toResponseEventDto(Event event) {
        return new ResponseEventDto(
                event.getId(),
                event.getAnnotation(),
                event.getCategory(),
                event.getConfirmedRequests(),
                event.getCreatedOn(),
                event.getDescription(),
                event.getEventDate(),
                event.getInitiator(),
                event.getLocation(),
                event.isPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.isRequestModeration(),
                event.getState(),
                event.getTitle(),
                event.getViews()
        );
    }

    public static ShortResponseEventDto toShortResponseEventDto(Event event) {
        return new ShortResponseEventDto(
                event.getId(),
                event.getAnnotation(),
                event.getCategory(),
                event.getConfirmedRequests(),
                event.getEventDate(),
                event.getInitiator(),
                event.isPaid(),
                event.getTitle(),
                event.getViews()
        );
    }
}
