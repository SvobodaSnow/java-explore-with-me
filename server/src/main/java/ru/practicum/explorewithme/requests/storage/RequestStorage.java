package ru.practicum.explorewithme.requests.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.requests.model.Request;

import java.util.List;

public interface RequestStorage extends JpaRepository<Request, Long> {
    Request findByIdAndRequester_Id(Long id, Long id1);

    List<Request> findByEventId(Long eventId);

    List<Request> findByRequester_Id(Long id);
}
