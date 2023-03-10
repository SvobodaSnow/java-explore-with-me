package ru.practicum.explorewithme.stats.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.stats.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointHitStorage extends JpaRepository<EndpointHit, Long> {
    List<EndpointHit> findByUriAndTimestampBetween(String s, LocalDateTime startTime, LocalDateTime endTime);
}
