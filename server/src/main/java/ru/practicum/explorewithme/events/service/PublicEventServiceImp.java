package ru.practicum.explorewithme.events.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.client.StatsClient;
import ru.practicum.explorewithme.client.model.EndpointHit;
import ru.practicum.explorewithme.events.dto.EventMapper;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.dto.ShortResponseEventDto;
import ru.practicum.explorewithme.events.model.Event;
import ru.practicum.explorewithme.events.model.State;
import ru.practicum.explorewithme.events.storage.EventStorage;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class PublicEventServiceImp implements PublicEventService {
    private final StatsClient statsClient;
    @Autowired
    private EventStorage eventStorage;

    @Override
    public List<ShortResponseEventDto> getEvents(
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
    ) {
        int page = from / size;
        List<Event> events;
        LocalDateTime rangeStartTime = LocalDateTime.parse(rangeStart);
        LocalDateTime rangeEndTime = LocalDateTime.parse(rangeEnd);

        if (onlyAvailable) {
            events = eventStorage.getEventsOnlyAvailable(
                    text,
                    text,
                    categories,
                    paid,
                    rangeStartTime,
                    rangeEndTime,
                    State.PUBLISHED,
                    PageRequest.of(page, size)
            );
        } else {
            events = eventStorage.getEvents(
                    text,
                    text,
                    categories,
                    paid,
                    rangeStartTime,
                    rangeEndTime,
                    State.PUBLISHED,
                    PageRequest.of(page, size)
            );
        }

        List<ShortResponseEventDto> shortResponseEventDtoList = new ArrayList<>();
        StringBuilder app = new StringBuilder();

        for (Event event : events) {
            event.setViews(event.getViews() + 1);
            shortResponseEventDtoList.add(EventMapper.toShortResponseEventDto(event));
            app.append("GetEvent ").append(event.getId()).append(", ");
        }

        eventStorage.saveAll(events);

        if (app.length() > 0) {
            app.delete(app.length() - 2, app.length());
        }

        EndpointHit endpointHit = new EndpointHit(null, app.toString(), request.getRequestURI(), request.getRemoteAddr(), LocalDateTime.now());
        statsClient.sendStates(endpointHit);

        switch (sort) {
            case "EVENT_DATE":
                Comparator<ShortResponseEventDto> sortEventDate = (o1, o2) ->
                        o1.getEventDate().compareTo(o2.getEventDate());
                shortResponseEventDtoList.sort(sortEventDate);
                break;
            case "VIEWS":
                Comparator<ShortResponseEventDto> sortViews = (o1, o2) ->
                        (int) (o1.getViews() - o2.getViews());
                shortResponseEventDtoList.sort(sortViews);
                break;
            default:
                //Добавить ошибку
        }

        return shortResponseEventDtoList;
    }

    @Override
    public ResponseEventDto getEventById(Long id, HttpServletRequest request) {
        Event event = eventStorage.findByIdAndState(id, State.PUBLISHED);
        EndpointHit endpointHit = new EndpointHit(
                null,
                "GetEvent " + id,
                request.getRequestURI(),
                request.getRemoteAddr(),
                LocalDateTime.now()
        );
        statsClient.sendStates(endpointHit);
        event.setViews(event.getViews() + 1);
        return EventMapper.toResponseEventDto(eventStorage.save(event));
    }
}
