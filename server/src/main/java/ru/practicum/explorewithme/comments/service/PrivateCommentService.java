package ru.practicum.explorewithme.comments.service;

import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.dto.NewCommentDto;
import ru.practicum.explorewithme.comments.dto.UpdateCommentDto;

public interface PrivateCommentService {
    CommentDto addNewComment(NewCommentDto newCommentDto, Long userId, Long eventId);

    CommentDto updateComment(UpdateCommentDto updateCommentDto, Long userId, Long eventId);
}
