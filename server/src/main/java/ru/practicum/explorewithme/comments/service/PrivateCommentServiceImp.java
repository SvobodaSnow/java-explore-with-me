package ru.practicum.explorewithme.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.dto.CommentMapper;
import ru.practicum.explorewithme.comments.dto.NewCommentDto;
import ru.practicum.explorewithme.comments.model.Comment;
import ru.practicum.explorewithme.comments.storage.CommentStorage;
import ru.practicum.explorewithme.users.model.User;
import ru.practicum.explorewithme.users.storage.UserStorage;

@Service
@Primary
public class PrivateCommentServiceImp implements PrivateCommentService {
    @Autowired
    private CommentStorage commentStorage;
    @Autowired
    private UserStorage userStorage;

    @Override
    public CommentDto addNewComment(NewCommentDto newCommentDto, Long userId, Long eventId) {
        User author = userStorage.getById(userId);
        Comment newComment = CommentMapper.toComment(newCommentDto, author, eventId);
        return CommentMapper.toCommentDto(commentStorage.save(newComment));
    }
}
