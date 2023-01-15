package ru.practicum.explorewithme.requests.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.requests.model.Status;

import java.time.LocalDateTime;

import static ru.practicum.explorewithme.client.model.GlobalVariables.PATTERN_DATE_TIME;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private Long id;
    private Long event;
    private Long requester;
    @JsonFormat(pattern = PATTERN_DATE_TIME)
    private LocalDateTime created;
    private Status status;
}
