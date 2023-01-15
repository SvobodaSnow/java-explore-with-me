package ru.practicum.explorewithme.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.categories.storage.CategoriesStorage;
import ru.practicum.explorewithme.events.dto.*;
import ru.practicum.explorewithme.events.model.Event;
import ru.practicum.explorewithme.events.model.State;
import ru.practicum.explorewithme.events.storage.EventStorage;
import ru.practicum.explorewithme.events.storage.LocationStorage;
import ru.practicum.explorewithme.exceptions.model.ValidationException;
import ru.practicum.explorewithme.users.model.User;
import ru.practicum.explorewithme.users.storage.UserStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class PrivateEventServiceImp implements PrivateEventService {
    @Autowired
    private EventStorage eventStorage;
    @Autowired
    private LocationStorage locationStorage;
    @Autowired
    private UserStorage userStorage;
    @Autowired
    private CategoriesStorage categoriesStorage;

    @Override
    public ResponseEventDto createNewEvent(NewEventDto newEventDto, Long userId) {
        try {
            if (newEventDto.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
                throw new ValidationException(
                        "Событие будет раньше чем через 2 часа",
                        "Не верно указано время события",
                        LocalDateTime.now()
                );
            }
            User initiator = userStorage.getById(userId);
            Categories categories = categoriesStorage.getById(newEventDto.getCategory());
            Event event = EventMapper.toNewEvent(newEventDto, initiator, categories);
            event.setLocation(locationStorage.save(event.getLocation()));
            return EventMapper.toResponseEventDto(eventStorage.save(event));
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException(
                    "Не верно составлен запрос",
                    "Не указаны обязательные параметры для создания события",
                    LocalDateTime.now()
            );
        }
    }

    @Override
    public List<ShortResponseEventDto> getEventByInitiatorId(Long initiatorId) {
        List<Event> events = eventStorage.findByInitiatorId(initiatorId);
        List<ShortResponseEventDto> eventDtoList = new ArrayList<>();
        for (Event event : events) {
            eventDtoList.add(EventMapper.toShortResponseEventDto(event));
        }
        return eventDtoList;
    }

    @Override
    public ResponseEventDto updateEvent(UpdateEventDto updateEventDto, Long userId) {
        Event event = eventStorage.getById(updateEventDto.getEventId());
        if (updateEventDto.getAnnotation() != null && !updateEventDto.getAnnotation().isEmpty()) {
            event.setAnnotation(updateEventDto.getAnnotation());
        }
        if (updateEventDto.getCategory() != null) {
            event.setCategory(categoriesStorage.getById(updateEventDto.getCategory()));
        }
        if (updateEventDto.getDescription() != null && !updateEventDto.getDescription().isEmpty()) {
            event.setDescription(updateEventDto.getDescription());
        }
        if (updateEventDto.getEventDate() != null) {
            event.setEventDate(updateEventDto.getEventDate());
        }
        if (updateEventDto.isPaid() != event.isPaid()) {
            event.setPaid(updateEventDto.isPaid());
        }
        if (updateEventDto.getParticipantLimit() != event.getParticipantLimit()) {
            event.setParticipantLimit(updateEventDto.getParticipantLimit());
        }
        if (updateEventDto.getTitle() != null && !updateEventDto.getTitle().isEmpty()) {
            event.setTitle(updateEventDto.getTitle());
        }
        return EventMapper.toResponseEventDto(eventStorage.save(event));
    }

    @Override
    public ResponseEventDto getEventById(Long userId, Long eventId) {
        return EventMapper.toResponseEventDto(eventStorage.getById(eventId));
    }

    @Override
    public ResponseEventDto cancelEventById(Long userId, Long eventId) {
        Event event = eventStorage.getById(eventId);
        event.setState(State.CANCELED);
        return EventMapper.toResponseEventDto(eventStorage.save(event));
    }
}
