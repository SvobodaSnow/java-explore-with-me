package ru.practicum.explorewithme.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.dto.NewCommentDto;
import ru.practicum.explorewithme.comments.service.PrivateCommentService;

import javax.validation.constraints.Positive;
import java.text.MessageFormat;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/users/{userId}/comments/{eventId}")
public class PrivateCommentController {
    @Autowired
    private PrivateCommentService privateCommentService;

    @PostMapping
    public CommentDto addNewComment(
            @RequestBody NewCommentDto commentDto,
            @Positive @PathVariable Long userId,
            @Positive @PathVariable Long eventId
    ) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на добавление нового коментария. ID пользователя: {0}. ID события: {1}",
                        userId,
                        eventId
                )
        );
        return privateCommentService.addNewComment(commentDto, userId, eventId);
    }
}