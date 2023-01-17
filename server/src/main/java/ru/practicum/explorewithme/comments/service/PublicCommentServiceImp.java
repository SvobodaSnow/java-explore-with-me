package ru.practicum.explorewithme.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.dto.CommentMapper;
import ru.practicum.explorewithme.comments.model.Status;
import ru.practicum.explorewithme.comments.storage.CommentStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class PublicCommentServiceImp implements PublicCommentService {
    @Autowired
    private CommentStorage commentStorage;

    @Override
    public List<CommentDto> getAllCommentByEventId(Long eventId, int from, int size) {
        int page = from / size;
        return commentStorage.findByEventIdAndStatus(eventId, Status.PUBLISHED, PageRequest.of(page, size))
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }
}
