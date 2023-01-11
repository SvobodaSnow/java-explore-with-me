package ru.practicum.explorewithme.events.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.events.model.Location;

public interface LocationStorage extends JpaRepository<Location, Long> {
}
