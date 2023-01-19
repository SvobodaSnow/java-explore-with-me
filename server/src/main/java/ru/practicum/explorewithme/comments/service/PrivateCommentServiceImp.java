package ru.practicum.explorewithme.comments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.dto.CommentMapper;
import ru.practicum.explorewithme.comments.dto.NewCommentDto;
import ru.practicum.explorewithme.comments.dto.UpdateCommentDto;
import ru.practicum.explorewithme.comments.model.Comment;
import ru.practicum.explorewithme.comments.storage.CommentStorage;
import ru.practicum.explorewithme.users.model.User;
import ru.practicum.explorewithme.users.storage.UserStorage;

@Service
@Primary
@RequiredArgsConstructor
public class PrivateCommentServiceImp implements PrivateCommentService {
    private final CommentStorage commentStorage;
    private final UserStorage userStorage;

    @Override
    public CommentDto addNewComment(NewCommentDto newCommentDto, Long userId, Long eventId) {
        User author = userStorage.getById(userId);
        Comment newComment = CommentMapper.toComment(newCommentDto, author, eventId);
        return CommentMapper.toCommentDto(commentStorage.save(newComment));
    }

    @Override
    public CommentDto updateComment(UpdateCommentDto updateCommentDto, Long userId, Long eventId) {
        User author = userStorage.getById(userId);
        Comment oldComment = commentStorage.getById(updateCommentDto.getId());
        Comment updateComment = CommentMapper.toComment(updateCommentDto, author, eventId, oldComment.getCreated());
        return CommentMapper.toCommentDto(commentStorage.save(updateComment));
    }
}
