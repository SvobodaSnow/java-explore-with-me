package ru.practicum.explorewithme.events.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventDto {
    private Long eventId;
    private String annotation;
    private Long category;
    private String description;
    private LocalDateTime eventDate;
    private boolean paid;
    private int participantLimit;
    private String title;
}
