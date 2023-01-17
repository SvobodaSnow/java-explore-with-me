package ru.practicum.explorewithme.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.model.Status;
import ru.practicum.explorewithme.comments.service.AdminCommentService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/comments")
public class AdminCommentController {
    @Autowired
    private AdminCommentService adminCommentService;

    @GetMapping
    public List<List<CommentDto>> getCommentForEvents(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(defaultValue = "PENDING") List<Status> statuses,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на формирования списка комментариев от администратора. " +
                                "ID событий : {0}. Статусы: {1}",
                        ids,
                        statuses
                )
        );
        return adminCommentService.getCommentForEvents(ids, statuses);
    }

    @PatchMapping("/{commentId}/publish")
    public CommentDto publishComment(@Positive @PathVariable Long commentId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос от администратора на публикацию комментария с ID {0}",
                        commentId
                )
        );
        return adminCommentService.publishComment(commentId);
    }

    @PatchMapping("/{commentId}/reject")
    public CommentDto rejectComment(@Positive @PathVariable Long commentId) {
        log.info(
                MessageFormat.format(
                        "Получен запрос от администратора на отмену комментария с ID {0}",
                        commentId
                )
        );
        return adminCommentService.rejectComment(commentId);
    }
}
