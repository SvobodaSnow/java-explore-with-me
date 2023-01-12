package ru.practicum.explorewithme.stats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.stats.model.EndpointHit;
import ru.practicum.explorewithme.stats.model.ViewStats;
import ru.practicum.explorewithme.stats.storage.EndpointHitStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@Primary
public class StatsServiceImp implements StatsService {
    @Autowired
    private EndpointHitStorage endpointHitStorage;

    @Override
    public void saveStatistic(EndpointHit endpointHit) {
        endpointHitStorage.save(endpointHit);
    }

    @Override
    public ViewStats getStats(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<EndpointHit> endpointHit = endpointHitStorage.findByUriInIgnoreCaseAndTimestampBetween(
                uris,
                startTime,
                endTime
        );
        ViewStats viewStats;
        if (endpointHit.size() > 0) {
            Collection<EndpointHit> endpointHitCollection;
            if (unique) {
                endpointHitCollection = new HashSet<>(endpointHit);
            } else {
                endpointHitCollection = endpointHit;
            }
            viewStats = new ViewStats(
                    endpointHit.get(0).getApp(),
                    uris.get(0),
                    endpointHitCollection.size()
            );
        } else {
            viewStats = new ViewStats(
                    "",
                    uris.get(0),
                    0
            );
        }

        return viewStats;
    }
}
