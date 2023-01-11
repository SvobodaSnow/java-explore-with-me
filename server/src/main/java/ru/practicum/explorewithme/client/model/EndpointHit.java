package ru.practicum.explorewithme.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class EndpointHit {
    private Long id;
    private String app;
    private String uri;
    private String ip;
    private LocalDateTime timestamp;
}
