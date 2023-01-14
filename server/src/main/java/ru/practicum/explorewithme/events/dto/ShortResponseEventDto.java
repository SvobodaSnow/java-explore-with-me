package ru.practicum.explorewithme.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.users.model.User;

import java.time.LocalDateTime;

import static ru.practicum.explorewithme.client.model.GlobalVariables.PATTERN_DATE_TIME;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortResponseEventDto {
    private Long id;
    private String annotation;
    private Categories category;
    private int confirmedRequests;
    @JsonFormat(pattern = PATTERN_DATE_TIME)
    private LocalDateTime eventDate;
    private User initiator;
    private boolean paid;
    private String title;
    private Long views;
}
