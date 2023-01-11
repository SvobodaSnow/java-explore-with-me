package ru.practicum.explorewithme.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.categories.storage.CategoriesStorage;
import ru.practicum.explorewithme.events.dto.EventMapper;
import ru.practicum.explorewithme.events.dto.NewEventDto;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.model.Event;
import ru.practicum.explorewithme.events.model.State;
import ru.practicum.explorewithme.events.storage.EventStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class AdminEventServiceImp implements AdminEventService {
    @Autowired
    private EventStorage eventStorage;
    @Autowired
    private CategoriesStorage categoriesStorage;

    @Override
    public List<ResponseEventDto> getEvents(
            List<Long> users,
            List<State> states,
            List<Long> categories,
            String rangeStart,
            String rangeEnd,
            int from,
            int size
    ) {
        LocalDateTime rangeStartTime = LocalDateTime.parse(
                rangeStart,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        LocalDateTime rangeEndTime = LocalDateTime.parse(
                rangeEnd,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        int page = from / size;
        List<Event> events;
        if (users == null && states == null && categories == null) {
            events = eventStorage.findAll();
        } else {
            events = eventStorage.findByInitiator_IdInAndStateInAndCategory_IdInAndEventDateBetween(
                    users,
                    states,
                    categories,
                    rangeStartTime,
                    rangeEndTime,
                    PageRequest.of(page, size)
            );
        }

        List<ResponseEventDto> responseEventDtoList = new ArrayList<>();
        for (Event event : events) {
            responseEventDtoList.add(EventMapper.toResponseEventDto(event));
        }
        return responseEventDtoList;
    }

    @Override
    public ResponseEventDto updateEvent(NewEventDto newEventDto, Long eventId) {
        Event event = eventStorage.getById(eventId);
        if (newEventDto.getAnnotation() != null && !newEventDto.getAnnotation().isEmpty()) {
            event.setAnnotation(newEventDto.getAnnotation());
        }
        if (newEventDto.getCategory() != null) {
            event.setCategory(categoriesStorage.getById(newEventDto.getCategory()));
        }
        if (newEventDto.getDescription() != null && !newEventDto.getDescription().isEmpty()) {
            event.setDescription(newEventDto.getDescription());
        }
        if (newEventDto.getEventDate() != null) {
            event.setEventDate(newEventDto.getEventDate());
        }
        if (newEventDto.isPaid() != event.isPaid()) {
            event.setPaid(newEventDto.isPaid());
        }
        if (newEventDto.getParticipantLimit() != event.getParticipantLimit()) {
            event.setParticipantLimit(newEventDto.getParticipantLimit());
        }
        if (newEventDto.getTitle() != null && !newEventDto.getTitle().isEmpty()) {
            event.setTitle(newEventDto.getTitle());
        }
        return EventMapper.toResponseEventDto(eventStorage.save(event));
    }

    @Override
    public ResponseEventDto publishEvent(Long eventId) {
        Event event = eventStorage.getById(eventId);
        event.setState(State.PUBLISHED);
        return EventMapper.toResponseEventDto(eventStorage.save(event));
    }

    @Override
    public ResponseEventDto rejectEvent(Long eventId) {
        Event event = eventStorage.getById(eventId);
        event.setState(State.CANCELED);
        return EventMapper.toResponseEventDto(eventStorage.save(event));
    }
}
