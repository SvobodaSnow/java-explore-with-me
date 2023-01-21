package ru.practicum.explorewithme.comments.service;

import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.model.Status;

import java.util.List;

public interface AdminCommentService {
    List<List<CommentDto>> getCommentForEvents(List<Long> ids, List<Status> statuses);

    CommentDto publishComment(Long commentId);

    CommentDto rejectComment(Long commentId);
}
