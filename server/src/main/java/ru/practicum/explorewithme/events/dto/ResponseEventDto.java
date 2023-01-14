package ru.practicum.explorewithme.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.events.model.Location;
import ru.practicum.explorewithme.events.model.State;
import ru.practicum.explorewithme.users.model.User;

import java.time.LocalDateTime;

import static ru.practicum.explorewithme.client.model.GlobalVariables.PATTERN_DATE_TIME;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResponseEventDto {
    private Long id;
    private String annotation;
    private Categories category;
    private int confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    @JsonFormat(pattern = PATTERN_DATE_TIME)
    private LocalDateTime eventDate;
    private User initiator;
    private Location location;
    private String paid;
    private String participantLimit;
    private LocalDateTime publishedOn;
    private String requestModeration;
    private State state;
    private String title;
    private Long views;
}
