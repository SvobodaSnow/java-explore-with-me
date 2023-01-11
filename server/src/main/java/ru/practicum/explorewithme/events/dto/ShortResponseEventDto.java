package ru.practicum.explorewithme.events.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.users.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortResponseEventDto {
    private Long id;
    private String annotation;
    private Categories category;
    private int confirmedRequests;
    private LocalDateTime eventDate;
    private User initiator;
    private boolean paid;
    private String title;
    private Long views;
}
