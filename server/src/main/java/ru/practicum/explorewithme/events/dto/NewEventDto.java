package ru.practicum.explorewithme.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.events.model.Location;

import java.time.LocalDateTime;

import static ru.practicum.explorewithme.client.model.GlobalVariables.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewEventDto {
    private String annotation;
    private Long category;
    private String description;
    @JsonFormat(pattern = PATTERN_DATE_TIME)
    private LocalDateTime eventDate;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private boolean requestModeration;
    private String title;
}
