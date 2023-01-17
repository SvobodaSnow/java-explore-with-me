package ru.practicum.explorewithme.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.dto.CommentMapper;
import ru.practicum.explorewithme.comments.model.Comment;
import ru.practicum.explorewithme.comments.model.Status;
import ru.practicum.explorewithme.comments.storage.CommentStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class AdminCommentServiceImp implements AdminCommentService {
    @Autowired
    private CommentStorage commentStorage;

    @Override
    public List<List<CommentDto>> getCommentForEvents(List<Long> ids, List<Status> statuses) {
        List<List<CommentDto>> commentsDto = new ArrayList<>();
        for (Long id : ids) {
            List<Comment> comments = commentStorage.findByEventIdAndStatusIn(id, statuses);
            List<CommentDto> commentDtoList = new ArrayList<>();
            for (Comment comment : comments) {
                commentDtoList.add(CommentMapper.toCommentDto(comment));
            }
            commentsDto.add(commentDtoList);
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
