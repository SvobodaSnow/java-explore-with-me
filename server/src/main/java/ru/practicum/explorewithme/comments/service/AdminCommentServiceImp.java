package ru.practicum.explorewithme.comments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.dto.CommentMapper;
import ru.practicum.explorewithme.comments.model.Comment;
import ru.practicum.explorewithme.comments.model.Status;
import ru.practicum.explorewithme.comments.storage.CommentStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class AdminCommentServiceImp implements AdminCommentService {
    private final CommentStorage commentStorage;

    @Override
    public List<List<CommentDto>> getCommentForEvents(List<Long> ids, List<Status> statuses) {
        List<List<CommentDto>> commentsDto = new ArrayList<>();
        if (ids != null) {
            for (Long id : ids) {
                commentsDto.add(commentStorage.findByEventIdAndStatusIn(id, statuses)
                        .stream()
                        .map(CommentMapper::toCommentDto)
                        .collect(Collectors.toList())
                );
            }
        } else {
            commentsDto.add(commentStorage.findByStatusIn(statuses)
                    .stream()
                    .map(CommentMapper::toCommentDto)
                    .collect(Collectors.toList())
            );
        }
        return commentsDto;
    }

    @Override
    public CommentDto publishComment(Long commentId) {
        Comment comment = commentStorage.getById(commentId);
        comment.setStatus(Status.PUBLISHED);
        return CommentMapper.toCommentDto(commentStorage.save(comment));
    }

    @Override
    public CommentDto rejectComment(Long commentId) {
        Comment comment = commentStorage.getById(commentId);
        comment.setStatus(Status.CANCELED);
        return CommentMapper.toCommentDto(commentStorage.save(comment));
    }
}
