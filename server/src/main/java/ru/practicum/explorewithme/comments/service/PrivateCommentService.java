package ru.practicum.explorewithme.comments.service;

import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.dto.NewCommentDto;

public interface PrivateCommentService {
    CommentDto addNewComment(NewCommentDto newCommentDto, Long userId, Long eventId);
}
