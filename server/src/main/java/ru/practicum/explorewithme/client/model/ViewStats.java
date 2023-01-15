package ru.practicum.explorewithme.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ViewStats {
    private String app;
    private String uri;
    private int hits;
}
