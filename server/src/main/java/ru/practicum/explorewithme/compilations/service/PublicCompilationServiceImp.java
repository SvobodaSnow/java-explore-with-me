package ru.practicum.explorewithme.compilations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.compilations.dto.CompilationMapper;
import ru.practicum.explorewithme.compilations.dto.ResponseCompilationDto;
import ru.practicum.explorewithme.compilations.model.Compilation;
import ru.practicum.explorewithme.compilations.storage.CompilationStorage;
import ru.practicum.explorewithme.events.dto.EventMapper;
import ru.practicum.explorewithme.events.dto.ResponseEventDto;
import ru.practicum.explorewithme.events.model.Event;
import ru.practicum.explorewithme.events.storage.EventStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class PublicCompilationServiceImp implements PublicCompilationService {
    @Autowired
    private CompilationStorage compilationStorage;
    @Autowired
    private EventStorage eventStorage;

    @Override
    public List<ResponseCompilationDto> getCompilations(Boolean pinned, int from, int size) {
        int page = from / size;
        List<Compilation> compilations;
        if (pinned == null) {
            compilations = compilationStorage.findAll(PageRequest.of(page, size)).getContent();
        } else {
            compilations = compilationStorage.findByPinned(pinned, PageRequest.of(page, size));
        }
        List<ResponseCompilationDto> responseCompilationDtoList = new ArrayList<>();
        for (Compilation compilation : compilations) {
            List<ResponseEventDto> responseEventDtoList = new ArrayList<>();
            for (Event event : compilation.getEvents()) {
                event.setViews(event.getViews() + 1);
                responseEventDtoList.add(EventMapper.toResponseEventDto(event));
            }
            eventStorage.saveAll(compilation.getEvents());
            responseCompilationDtoList.add(CompilationMapper.toResponseCompilationDto(
                            compilation,
                            responseEventDtoList
                    )
            );
        }
        return responseCompilationDtoList;
    }

    @Override
    public ResponseCompilationDto getCompilationById(Long compId) {
        Compilation compilation = compilationStorage.getById(compId);
        List<ResponseEventDto> responseEventDtoList = new ArrayList<>();
        for (Event event : compilation.getEvents()) {
            event.setViews(event.getViews() + 1);
            responseEventDtoList.add(EventMapper.toResponseEventDto(event));
        }
        eventStorage.saveAll(compilation.getEvents());
        return CompilationMapper.toResponseCompilationDto(compilation, responseEventDtoList);
    }
}
