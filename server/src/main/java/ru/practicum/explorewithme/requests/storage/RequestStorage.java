package ru.practicum.explorewithme.requests.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.requests.model.Request;

import java.util.List;

public interface RequestStorage extends JpaRepository<Request, Long> {
    List<Request> findByEventId(Long eventId);
    Request findByRequester_IdAndEventId(Long id, Long eventId);
    List<Request> findByRequester_Id(Long id);

}
