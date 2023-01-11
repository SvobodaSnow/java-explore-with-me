package ru.practicum.explorewithme.compilations.service;

import ru.practicum.explorewithme.compilations.dto.ResponseCompilationDto;

import java.util.List;

public interface PublicCompilationService {
    List<ResponseCompilationDto> getCompilations(boolean pinned, int from, int size);

    ResponseCompilationDto getCompilationById(Long compId);
}
