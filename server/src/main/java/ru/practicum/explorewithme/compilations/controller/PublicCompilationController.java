package ru.practicum.explorewithme.compilations.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.compilations.dto.ResponseCompilationDto;
import ru.practicum.explorewithme.compilations.service.PublicCompilationService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/compilations")
public class PublicCompilationController {
    @Autowired
    private PublicCompilationService publicCompilationService;

    @GetMapping
    public List<ResponseCompilationDto> getCompilations(
            @RequestParam boolean pinned,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен публичный запрос на получение подборок. Pinned: " + pinned);
        return publicCompilationService.getCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public ResponseCompilationDto getCompilationById(@PathVariable Long compId) {
        log.info("Получен публичный запрос на отправку подборки с ID " + compId);
        return publicCompilationService.getCompilationById(compId);
    }
}
