package ru.practicum.explorewithme.compilations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.compilations.dto.CompilationDto;
import ru.practicum.explorewithme.compilations.dto.CompilationMapper;
import ru.practicum.explorewithme.compilations.model.Compilation;
import ru.practicum.explorewithme.compilations.storage.CompilationStorage;
import ru.practicum.explorewithme.events.model.Event;
import ru.practicum.explorewithme.events.storage.EventStorage;
import ru.practicum.explorewithme.exceptions.model.ValidationException;

import java.time.LocalDateTime;
import java.util.List;

@Primary
@Service
public class AdminCompilationServiceImp implements AdminCompilationService {
    @Autowired
    private CompilationStorage compilationStorage;
    @Autowired
    private EventStorage eventStorage;

    @Override
    public Compilation addNewCompilation(CompilationDto compilationDto) {
        try {
            List<Event> events = eventStorage.findAllById(compilationDto.getEvents());
            Compilation compilation = CompilationMapper.toCompilation(compilationDto, events);
            return compilationStorage.save(compilation);
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException(
                    "Отсутствует список событий",
                    "Список событий пуст",
                    LocalDateTime.now()
            );
        }
    }

    @Override
    public void deleteCompilation(Long compId) {
        compilationStorage.deleteById(compId);
    }

    @Override
    public void deleteEventFromCompilation(Long compId, Long eventId) {
        Compilation compilation = compilationStorage.getById(compId);
        compilation.getEvents().remove(eventStorage.getById(eventId));
        compilationStorage.save(compilation);
    }

    @Override
    public void addEventFromCompilation(Long compId, Long eventId) {
        Compilation compilation = compilationStorage.getById(compId);
        Event event = eventStorage.getById(eventId);
        compilation.getEvents().add(event);
        compilationStorage.save(compilation);
    }

    @Override
    public void unpinCompilation(Long compId) {
        Compilation compilation = compilationStorage.getById(compId);
        compilation.setPinned(false);
        compilationStorage.save(compilation);
    }

    @Override
    public void pinCompilation(Long compId) {
        Compilation compilation = compilationStorage.getById(compId);
        compilation.setPinned(true);
        compilationStorage.save(compilation);
    }
}
