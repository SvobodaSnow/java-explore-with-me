package ru.practicum.explorewithme.compilations.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.compilations.dto.ResponseCompilationDto;
import ru.practicum.explorewithme.compilations.service.PublicCompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.text.MessageFormat;
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
            @RequestParam(required = false) Boolean pinned,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        log.info(MessageFormat.format("Получен публичный запрос на получение подборок. Pinned: {0}", pinned));
        return publicCompilationService.getCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public ResponseCompilationDto getCompilationById(@Positive @PathVariable Long compId) {
        log.info(MessageFormat.format("Получен публичный запрос на отправку подборки с ID {0}", compId));
        return publicCompilationService.getCompilationById(compId);
    }
}
