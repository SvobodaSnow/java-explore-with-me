package ru.practicum.explorewithme.events.storage;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explorewithme.events.model.Event;
import ru.practicum.explorewithme.events.model.State;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface EventStorage extends JpaRepository<Event, Long> {
    @Query("select e from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?2, '%'))) " +
            "and e.eventDate between ?3 and ?4 " +
            "and e.state = ?5 " +
            "and e.available = true " +
            "order by e.eventDate")
    List<Event> getEventsOnlyAvailableOrderByEventDate(
            String annotation,
            String description,
            LocalDateTime eventDateStart,
            LocalDateTime eventDateEnd,
            State state,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?2, '%'))) " +
            "and e.eventDate between ?3 and ?4 " +
            "and e.state = ?5 " +
            "order by e.eventDate")
    List<Event> getEventsOrderByEventDate(
            String annotation,
            String description,
            LocalDateTime eventDateStart,
            LocalDateTime eventDateEnd,
            State state,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?2, '%'))) " +
            "and e.category.id in ?3 " +
            "and e.paid = ?4 " +
            "and e.eventDate between ?5 and ?6 " +
            "and e.state = ?7 " +
            "and e.available = true " +
            "order by e.eventDate")
    List<Event> getEventsOnlyAvailableOrderByEventDate(
            String annotation,
            String description,
            Collection<Long> ids,
            Boolean paid,
            LocalDateTime eventDateStart,
            LocalDateTime eventDateEnd,
            State state,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?2, '%'))) " +
            "and e.category.id in ?3 " +
            "and e.paid = ?4 " +
            "and e.eventDate between ?5 and ?6 " +
            "and e.state = ?7 " +
            "order by e.eventDate")
    List<Event> getEventsOrderByEventDate(
            String annotation,
            String description,
            Collection<Long> ids,
            Boolean paid,
            LocalDateTime eventDateStart,
            LocalDateTime eventDateEnd,
            State state,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?2, '%'))) " +
            "and e.category.id in ?3 " +
            "and e.paid = ?4 " +
            "and e.eventDate between ?5 and ?6 " +
            "and e.state = ?7 " +
            "and e.available = true " +
            "order by e.views")
    List<Event> getEventsOnlyAvailableOrderByViews(
            String annotation,
            String description,
            Collection<Long> ids,
            Boolean paid,
            LocalDateTime eventDateStart,
            LocalDateTime eventDateEnd,
            State state,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?2, '%'))) " +
            "and e.category.id in ?3 " +
            "and e.paid = ?4 " +
            "and e.eventDate between ?5 and ?6 " +
            "and e.state = ?7 " +
            "order by e.views")
    List<Event> getEventsOrderByViews(
            String annotation,
            String description,
            Collection<Long> ids,
            Boolean paid,
            LocalDateTime eventDateStart,
            LocalDateTime eventDateEnd,
            State state,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?2, '%'))) " +
            "and e.eventDate between ?3 and ?4 " +
            "and e.state = ?5 " +
            "and e.available = true " +
            "order by e.views")
    List<Event> getEventsOnlyAvailableOrderByViews(
            String annotation,
            String description,
            LocalDateTime eventDateStart,
            LocalDateTime eventDateEnd,
            State state,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?2, '%'))) " +
            "and e.eventDate between ?3 and ?4 " +
            "and e.state = ?5 " +
            "order by e.views")
    List<Event> getEventsOrderByViews(
            String annotation,
            String description,
            LocalDateTime eventDateStart,
            LocalDateTime eventDateEnd,
            State state,
            Pageable pageable
    );

    Event findByIdAndState(Long id, State state);

    List<Event> findByInitiator_IdInAndStateInAndCategory_IdInAndEventDateBetween(
            List<Long> initiatorId,
            List<State> state,
            List<Long> categoryId,
            LocalDateTime rangeStart,
            LocalDateTime rangeEnd,
            Pageable pageable
    );

    List<Event> findByInitiatorId(Long initiatorId);

    List<Event> findByInitiator_IdInAndCategory_IdInAndEventDateBetween(
            List<Long> users,
            List<Long> categories,
            LocalDateTime rangeStartTime,
            LocalDateTime rangeEndTime,
            PageRequest of
    );
}
