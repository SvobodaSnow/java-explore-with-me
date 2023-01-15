package ru.practicum.explorewithme.requests.dto;

import ru.practicum.explorewithme.requests.model.Request;

public class RequestMapper {
    public static RequestDto toRequestDto(Request request) {
        return new RequestDto(
                request.getId(),
                request.getEventId(),
                request.getRequester().getId(),
                request.getCreated(),
                request.getStatus()
        );
    }
}
