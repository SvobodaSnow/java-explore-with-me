package ru.practicum.explorewithme.stats.service;

import ru.practicum.explorewithme.stats.model.EndpointHit;
import ru.practicum.explorewithme.stats.model.ViewStats;

import java.util.List;

public interface StatsService {
    void saveStatistic(EndpointHit endpointHit);

    List<ViewStats> getStats(String start, String end, List<String> uris, boolean unique);
}
