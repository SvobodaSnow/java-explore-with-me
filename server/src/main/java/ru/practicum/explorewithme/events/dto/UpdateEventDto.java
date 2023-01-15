package ru.practicum.explorewithme.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static ru.practicum.explorewithme.client.model.GlobalVariables.PATTERN_DATE_TIME;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventDto {
    private Long eventId;
    private String annotation;
    private Long category;
    private String description;
    @JsonFormat(pattern = PATTERN_DATE_TIME)
    private LocalDateTime eventDate;
    private boolean paid;
    private int participantLimit;
    private String title;
}
