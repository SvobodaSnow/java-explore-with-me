package ru.practicum.explorewithme.stats;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.stats.model.EndpointHit;
import ru.practicum.explorewithme.stats.model.ViewStats;
import ru.practicum.explorewithme.stats.service.StatsService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping
public class StatsController {
    @Autowired
    private StatsService statsService;

    @PostMapping("/hit")
    public boolean saveStatistic(@RequestBody EndpointHit endpointHit) {
        log.info("Получен запрос на сохранение информации о доступе к эндпоинту");
        statsService.saveStatistic(endpointHit);
        return true;
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam List<String> uris,
            @RequestParam boolean unique
    ) {
        log.info("Получен запрос на формитрвоание статистики. Начало: " + start + ". Конец: " + end +
                ". URIS: " + uris + ". Уникальность: " + unique);
        return statsService.getStats(start, end, uris, unique);
    }
}
