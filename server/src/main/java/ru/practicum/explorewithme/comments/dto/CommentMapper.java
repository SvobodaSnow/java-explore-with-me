package ru.practicum.explorewithme.comments.dto;

import ru.practicum.explorewithme.comments.model.Comment;
import ru.practicum.explorewithme.comments.model.Status;
import ru.practicum.explorewithme.users.model.User;

import java.time.LocalDateTime;

public class CommentMapper {
    public static Comment toComment(NewCommentDto newCommentDto, User author, Long eventId) {
        return new Comment(
                null,
                author,
                eventId,
                newCommentDto.getText(),
                LocalDateTime.now(),
                Status.PENDING
        );
    }

    public static CommentDto toCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getAuthor().getName(),
                comment.getCreated(),
                comment.getStatus()
        );
    }

    public static Comment toComment(
            UpdateCommentDto updateCommentDto,
            User author,
            Long eventId,
            LocalDateTime created
    ) {
        return new Comment(
                updateCommentDto.getId(),
                author,
                eventId,
                updateCommentDto.getText(),
                created,
                Status.PENDING
        );
    }
}
