package ru.practicum.explorewithme.comments.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.comments.model.Comment;
import ru.practicum.explorewithme.comments.model.Status;

import java.util.Collection;
import java.util.List;

public interface CommentStorage extends JpaRepository<Comment, Long> {
    List<Comment> findByEventIdAndStatusIn(Long eventId, Collection<Status> statuses);

    List<Comment> findByEventId(Long eventId, Pageable pageable);
}
