package ru.practicum.explorewithme.stats.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ViewStats {
    private String app;
    private String upi;
    private int hits;
}
