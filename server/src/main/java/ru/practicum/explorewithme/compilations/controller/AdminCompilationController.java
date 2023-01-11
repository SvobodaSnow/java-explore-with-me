package ru.practicum.explorewithme.compilations.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.compilations.dto.CompilationDto;
import ru.practicum.explorewithme.compilations.model.Compilation;
import ru.practicum.explorewithme.compilations.service.AdminCompilationService;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/compilations")
public class AdminCompilationController {
    @Autowired
    private AdminCompilationService adminCompilationService;

    @PostMapping
    public Compilation addNewCompilation(@RequestBody CompilationDto compilationDto) {
        log.info("Получен запрос на создание новой подборки событий");
        return adminCompilationService.addNewCompilation(compilationDto);
    }

    @DeleteMapping("/{compId}")
    public boolean deleteCompilation(@PathVariable Long compId) {
        log.info("Получен запрос на удаление подборки событий с ID " + compId);
        adminCompilationService.deleteCompilation(compId);
        return true;
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    public boolean deleteEventFromCompilation(@PathVariable Long compId, @PathVariable Long eventId) {
        log.info("Получен запрос на удаление события из подборки. ID подборки " + compId + ". ID события " + eventId);
        adminCompilationService.deleteEventFromCompilation(compId, eventId);
        return true;
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public boolean addEventFromCompilation(@PathVariable Long compId, @PathVariable Long eventId) {
        log.info("Получен запрос на добавления события в подборку. ID подборки " + compId + ". ID события " + eventId);
        adminCompilationService.addEventFromCompilation(compId, eventId);
        return true;
    }

    @DeleteMapping("/{compId}/pin")
    public boolean unpinCompilation(@PathVariable Long compId) {
        log.info("Получен запрос на открепление подборки с главного экрана. ID подборки " + compId);
        adminCompilationService.unpinCompilation(compId);
        return true;
    }

    @PatchMapping("/{compId}/pin")
    public boolean pinCompilation(@PathVariable Long compId) {
        log.info("Получен запрос на закрепление подборки на главном экране. ID подборки " + compId);
        adminCompilationService.pinCompilation(compId);
        return true;
    }
}
