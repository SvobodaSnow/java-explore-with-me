package ru.practicum.explorewithme.comments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.dto.NewCommentDto;
import ru.practicum.explorewithme.comments.dto.UpdateCommentDto;
import ru.practicum.explorewithme.comments.service.PrivateCommentService;

import javax.validation.constraints.Positive;
import java.text.MessageFormat;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/comments/{eventId}")
public class PrivateCommentController {
    private final PrivateCommentService privateCommentService;

    @PostMapping
    public CommentDto addNewComment(
            @RequestBody NewCommentDto commentDto,
            @Positive @PathVariable Long userId,
            @Positive @PathVariable Long eventId
    ) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на добавление нового комментария. ID пользователя: {0}. ID события: {1}",
                        userId,
                        eventId
                )
        );
        return privateCommentService.addNewComment(commentDto, userId, eventId);
    }

    @PatchMapping
    public CommentDto updateComment(
            @RequestBody UpdateCommentDto updateCommentDto,
            @Positive @PathVariable Long userId,
            @Positive @PathVariable Long eventId
    ) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на обновление комментария. ID пользователя: {0}. " +
                                "ID события: {1}. ID комментария {2}",
                        userId,
                        eventId,
                        updateCommentDto.getId()
                )
        );
        return privateCommentService.updateComment(updateCommentDto, userId, eventId);
    }
}
