package ru.practicum.explorewithme.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.comments.dto.CommentDto;
import ru.practicum.explorewithme.comments.service.PublicCommentService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/comments/{eventId}")
public class PublicCommentController {
    @Autowired
    private PublicCommentService publicCommentService;

    @GetMapping
    public List<CommentDto> getCommentByEventId(
            @PathVariable Long eventId,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на формирование списка коментариев для события с ID: {0}",
                        eventId
                )
        );
        return publicCommentService.getAllCommentByEventId(eventId, from, size);
    }
}
