package ru.practicum.explorewithme.compilations.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.compilations.model.Compilation;

import java.util.List;

public interface CompilationStorage extends JpaRepository<Compilation, Long> {
    List<Compilation> findByPinned(boolean pinned, Pageable pageable);
}
