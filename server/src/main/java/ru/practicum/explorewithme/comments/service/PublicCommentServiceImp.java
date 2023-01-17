package ru.practicum.explorewithme.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.model.Comment;
import ru.practicum.explorewithme.comments.storage.CommentStorage;

import java.util.List;

@Service
@Primary
public class PublicCommentServiceImp implements PublicCommentService {
    @Autowired
    private CommentStorage commentStorage;

    @Override
    public List<CommentDto> getAllCommentByEventId(Long eventId, int from, int size) {
        int page = from / size;
        List<Comment> comments = commentStorage.findByEventId(eventId, PageRequest.of(page, size));
        return null;
    }
}
