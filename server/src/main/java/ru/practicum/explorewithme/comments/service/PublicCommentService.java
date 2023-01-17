package ru.practicum.explorewithme.comments.service;

import ru.practicum.explorewithme.comments.dto.CommentDto;

import java.util.List;

public interface PublicCommentService {
    List<CommentDto> getAllCommentByEventId(Long eventId, int from, int size);
}
