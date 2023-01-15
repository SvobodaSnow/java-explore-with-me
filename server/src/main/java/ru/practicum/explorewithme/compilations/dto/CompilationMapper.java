package ru.practicum.explorewithme.compilations.dto;

import ru.practicum.explorewithme.compilations.model.Compilation;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.model.Event;

import java.util.List;

public class CompilationMapper {
    public static Compilation toCompilation(CompilationDto compilationDto, List<Event> events) {
        return new Compilation(
                null,
                events,
                compilationDto.isPinned(),
                compilationDto.getTitle()
        );
    }

    public static ResponseCompilationDto toResponseCompilationDto(
            Compilation compilation,
            List<ResponseEventDto> events
    ) {
        return new ResponseCompilationDto(
                compilation.getId(),
                events,
                compilation.isPinned(),
                compilation.getTitle()
        );
    }
}
