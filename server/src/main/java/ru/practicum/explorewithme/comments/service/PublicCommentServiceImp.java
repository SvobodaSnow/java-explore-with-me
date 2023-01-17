package ru.practicum.explorewithme.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
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
public class PublicCommentServiceImp implements PublicCommentService {
    @Autowired
    private CommentStorage commentStorage;

    @Override
    public List<CommentDto> getAllCommentByEventId(Long eventId, int from, int size) {
        int page = from / size;
        List<Comment> comments = commentStorage.findByEventIdAndStatus(
                eventId,
                Status.PUBLISHED,
                PageRequest.of(page, size)
        );
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtoList.add(CommentMapper.toCommentDto(comment));
        }
        return commentDtoList;
    }
}
