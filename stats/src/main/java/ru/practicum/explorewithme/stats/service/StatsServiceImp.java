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
    public List<ViewStats> getStats(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<ViewStats> viewStatsList = new ArrayList<>();
        for (String s : uris) {
            List<EndpointHit> endpointHit = endpointHitStorage.findByUriAndTimestampBetween(
                    s,
                    startTime,
                    endTime
            );
            if (endpointHit.size() > 0) {
                Collection<EndpointHit> endpointHitCollection;
                if (unique) {
                    endpointHitCollection = new HashSet<>(endpointHit);
                } else {
                    endpointHitCollection = endpointHit;
                }
                viewStatsList.add(
                        new ViewStats(
                                endpointHit.get(0).getApp(),
                                s,
                                endpointHitCollection.size()
                        )
                );
            } else {
                viewStatsList.add(
                        new ViewStats(
                                "",
                                s,
                                0
                        )
                );
            }
        }

        return viewStatsList;
    }
}
